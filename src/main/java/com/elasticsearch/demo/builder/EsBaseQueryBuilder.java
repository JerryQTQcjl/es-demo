package com.elasticsearch.demo.builder;

import com.elasticsearch.demo.exception.EsOptException;
import com.elasticsearch.demo.model.bo.page.EsBasePageQueryBO;
import com.elasticsearch.demo.model.dto.EsBaseSearchDTO;
import com.elasticsearch.demo.model.entiy.EsBaseEntity;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public interface EsBaseQueryBuilder<T extends EsBaseEntity> {

    SearchSourceBuilder buildReq(EsBasePageQueryBO<T> queryBO);

    default EsBaseSearchDTO<T> buildResp(Exception ex) {
        throw new EsOptException(ex);
    }

    EsBaseSearchDTO<T> buildResp(SearchResponse response);

}
