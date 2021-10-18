package com.elasticsearch.demo.dao;

import com.elasticsearch.demo.builder.EsBaseIndexBuilder;
import com.elasticsearch.demo.builder.EsBaseQueryBuilder;
import com.elasticsearch.demo.builder.EsBaseScrollBuilder;
import com.elasticsearch.demo.builder.EsBaseUpdateBuilder;
import com.elasticsearch.demo.model.bo.bulk.BulkOperationBO;
import com.elasticsearch.demo.model.bo.page.EsBasePageQueryBO;
import com.elasticsearch.demo.model.dto.EsBaseBulkOptDTO;
import com.elasticsearch.demo.model.dto.EsBaseSearchDTO;
import com.elasticsearch.demo.model.entiy.EsBaseEntity;

import java.util.List;

/**
 * @author jerry chan
 * @date 2021/10/12
 */
public interface EsBaseDao<T extends EsBaseEntity> {

    void create();

    void delete();

    EsBaseSearchDTO<T> pageSearch(EsBaseQueryBuilder<T> queryBuilder, EsBasePageQueryBO<T> queryBO);

    EsBaseSearchDTO<T> scrollSearch(EsBaseScrollBuilder<T> scrollBuilder, EsBaseQueryBuilder<T> queryBuilder, EsBasePageQueryBO<T> queryBO);

    void save(EsBaseIndexBuilder<T> indexBuilder, T t);

    void update(EsBaseUpdateBuilder<T> updateBuilder, T t);

    void deleteIndex(Long id);

    EsBaseBulkOptDTO bulkOperation(List<BulkOperationBO<T>> operations);

}
