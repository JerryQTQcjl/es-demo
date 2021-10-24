package com.elasticsearch.demo.builder.clue;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.elasticsearch.demo.exception.EsOptException;
import com.elasticsearch.demo.model.bo.page.EsBasePageQueryBO;
import com.elasticsearch.demo.model.dto.EsAfterSearchDTO;
import com.elasticsearch.demo.model.dto.EsBaseSearchDTO;
import com.elasticsearch.demo.model.entiy.ClueDetail;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author jerry chan
 * @date 2021/10/23
 */
public class EsClueDetailAfterSearchBuilder extends EsClueDetailPageQueryBuilder {

    @Override
    public SearchSourceBuilder buildReq(EsBasePageQueryBO<ClueDetail> queryBO) {
        if (CollectionUtil.isEmpty(queryBO.getSortParam())) {
            throw new EsOptException("search after es index [{}] sortParam can not be null");
        }
        BoolQueryBuilder boolQueryBuilder = buildCriteria(queryBO.getT());
        pageInfo.set(new EsAfterSearchDTO<ClueDetail>()
                .setSortParam(queryBO.getSortParam())
                .setPageSize(queryBO.getSize()));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .size(queryBO.getSize())
                .query(boolQueryBuilder);
        queryBO.getSortParam().forEach((field, order) -> searchSourceBuilder.sort(new FieldSortBuilder(field).order(SortOrder.valueOf(order))));
        Optional.ofNullable(queryBO.getSearchAfterParam()).ifPresent((param) -> searchSourceBuilder.searchAfter(param.values().toArray()));
        return searchSourceBuilder;
    }

    @Override
    public EsBaseSearchDTO<ClueDetail> buildResp(Exception ex) {
        pageInfo.remove();
        throw new EsOptException(ex);
    }

    @Override
    public EsBaseSearchDTO<ClueDetail> buildResp(SearchResponse response) {
        try {
            EsAfterSearchDTO<ClueDetail> esBaseSearchDTO = (EsAfterSearchDTO<ClueDetail>) pageInfo.get();
            SearchHits searchHits = response.getHits();
            esBaseSearchDTO.setTotal((int) searchHits.getTotalHits().value);

            SearchHit[] hits = searchHits.getHits();
            esBaseSearchDTO.setDataList(Arrays.stream(hits)
                    .map(hit -> BeanUtil.toBean(hit.getSourceAsMap(), ClueDetail.class))
                    .collect(Collectors.toList()));
            if (hits.length > 0) {
                //组装下次查询的searchAfterParam
                AtomicInteger index = new AtomicInteger(0);
                List<String> sortFields = new ArrayList<>(esBaseSearchDTO.getSortParam().keySet());
                LinkedHashMap<String, Object> searchAfterParam = new LinkedHashMap<>();
                Arrays.stream(hits[hits.length - 1].getSortValues()).forEach(val -> {
                    searchAfterParam.put(sortFields.get(index.getAndIncrement()), val);
                });
                esBaseSearchDTO.setSearchAfterParam(searchAfterParam);
            }
            return esBaseSearchDTO;
        } finally {
            pageInfo.remove();
        }
    }

    public static EsClueDetailAfterSearchBuilder getInstance() {
        return EsClueDetailAfterSearchBuilder.Singleton.INSTANCE.esClueDetailAfterSearchBuilder;
    }

    private enum Singleton {
        INSTANCE;
        private EsClueDetailAfterSearchBuilder esClueDetailAfterSearchBuilder = new EsClueDetailAfterSearchBuilder();
    }
}
