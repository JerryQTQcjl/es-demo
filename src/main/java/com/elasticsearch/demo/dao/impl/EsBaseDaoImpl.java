package com.elasticsearch.demo.dao.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.elasticsearch.demo.builder.EsBaseIndexBuilder;
import com.elasticsearch.demo.builder.EsBaseQueryBuilder;
import com.elasticsearch.demo.builder.EsBaseScrollBuilder;
import com.elasticsearch.demo.builder.EsBaseUpdateBuilder;
import com.elasticsearch.demo.dao.EsBaseDao;
import com.elasticsearch.demo.exception.EsOptException;
import com.elasticsearch.demo.factory.EsClientFactory;
import com.elasticsearch.demo.model.bo.bulk.BulkOperationBO;
import com.elasticsearch.demo.model.bo.page.EsBasePageQueryBO;
import com.elasticsearch.demo.model.dto.EsBaseBulkOptDTO;
import com.elasticsearch.demo.model.dto.EsBaseSearchDTO;
import com.elasticsearch.demo.model.dto.EsDefaultBulkOptDTO;
import com.elasticsearch.demo.model.entiy.EsBaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author jerry chan
 * @date 2021/10/12
 */
@Slf4j
public abstract class EsBaseDaoImpl<T extends EsBaseEntity> implements EsBaseDao<T> {

    private RestHighLevelClient esClient = EsClientFactory.getInstance();

    public abstract String getIndexName();

    public abstract Map<String, Object> getIndexMapping();

    public abstract Map<String, Object> getIndexSetting();

    @Override
    public void create() {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(getIndexName())
                .mapping(getIndexMapping())
                .settings(getIndexSetting());
        try {
            esClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("create es index [{}] fail", getIndexName(), e);
            throw new EsOptException(e);
        }
    }

    @Override
    public void delete() {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(getIndexName());
        try {
            esClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("delete es index [{}] fail", getIndexName(), e);
            throw new EsOptException(e);
        }
    }

    @Override
    public EsBaseSearchDTO<T> pageSearch(EsBaseQueryBuilder<T> queryBuilder, EsBasePageQueryBO<T> queryBO) {
        SearchRequest searchRequest = new SearchRequest()
                .indices(getIndexName())
                .source(queryBuilder.buildReq(queryBO));
        //scroll
        Optional.ofNullable(queryBO.getScrollTime()).ifPresent(searchRequest::scroll);
        try {
            SearchResponse searchResponse = esClient.search(searchRequest, RequestOptions.DEFAULT);
            return queryBuilder.buildResp(searchResponse);
        } catch (Exception e) {
            log.error("search es index [{}] fail, searchRequest: {}", getIndexName(), searchRequest, e);
            return queryBuilder.buildResp(e);
        }
    }

    @Override
    public EsBaseSearchDTO<T> scrollSearch(EsBaseScrollBuilder<T> scrollBuilder, EsBaseQueryBuilder<T> queryBuilder, EsBasePageQueryBO<T> queryBO) {
        if (StrUtil.isBlank(queryBO.getScrollId())) {
            return pageSearch(queryBuilder, queryBO);
        }
        SearchScrollRequest searchScrollRequest = scrollBuilder.buildReq(queryBO);
        try {
            SearchResponse searchResponse = esClient.scroll(searchScrollRequest, RequestOptions.DEFAULT);
            return queryBuilder.buildResp(searchResponse);
        } catch (Exception e) {
            log.error("scroll es index [{}] fail, searchScrollRequest: {}", getIndexName(), searchScrollRequest, e);
            return queryBuilder.buildResp(e);
        }
    }

    @Override
    public void save(EsBaseIndexBuilder<T> indexBuilder, T t) {
        IndexRequest indexRequest = indexBuilder.buildReq(t).index(getIndexName());
        try {
            IndexResponse indexResponse = esClient.index(indexRequest, RequestOptions.DEFAULT);
            Assert.isTrue(indexResponse.status().getStatus() == HttpStatus.HTTP_OK, () -> new EsOptException(indexResponse.toString()));
        } catch (Exception e) {
            log.error("saveOrUpdate es index [{}] fail, indexRequest: {}", getIndexName(), indexRequest, e);
            throw new EsOptException(e);
        }
    }

    @Override
    public void update(EsBaseUpdateBuilder<T> updateBuilder, T t) {
        UpdateRequest updateRequest = updateBuilder.buildReq(t).index(getIndexName());
        try {
            UpdateResponse updateResponse = esClient.update(updateRequest, RequestOptions.DEFAULT);
            Assert.isTrue(updateResponse.status().getStatus() == HttpStatus.HTTP_OK, () -> new EsOptException(updateResponse.toString()));
        } catch (Exception e) {
            log.error("update es index [{}] fail, updateRequest: {}", getIndexName(), updateRequest, e);
            throw new EsOptException(e);
        }
    }

    @Override
    public void deleteIndex(Long id) {
        DeleteRequest deleteRequest = new DeleteRequest().id(StrUtil.toString(id)).index(getIndexName());
        try {
            DeleteResponse deleteResponse = esClient.delete(deleteRequest, RequestOptions.DEFAULT);
            Assert.isTrue(deleteResponse.status().getStatus() == HttpStatus.HTTP_OK, () -> new EsOptException(deleteResponse.toString()));
        } catch (Exception e) {
            log.error("delete es index [{}] fail, deleteRequest: {}", getIndexName(), deleteRequest, e);
            throw new EsOptException(e);
        }
    }

    @Override
    public EsBaseBulkOptDTO bulkOperation(List<BulkOperationBO<T>> operations) {
        BulkRequest bulkRequest = new BulkRequest();
        operations.forEach(opt -> {
            opt.setIndex(getIndexName());
            opt.addRequest(bulkRequest);
        });
        try {
            BulkResponse bulkResponse = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            return new EsDefaultBulkOptDTO(bulkResponse);
        } catch (Exception e) {
            log.error("bulk operation es index [{}] fail, bulkRequest: {}", getIndexName(), bulkRequest, e);
            throw new EsOptException(e);
        }
    }
}
