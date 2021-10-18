package com.elasticsearch.demo.builder;

import com.elasticsearch.demo.exception.EsOptException;
import com.elasticsearch.demo.model.bo.page.EsBasePageQueryBO;
import com.elasticsearch.demo.model.dto.EsBaseSearchDTO;
import com.elasticsearch.demo.model.entiy.EsBaseEntity;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;

/**
 * @author jerry chan
 * @date 2021/10/14
 */
public interface EsBaseScrollBuilder<T extends EsBaseEntity> {

    default SearchScrollRequest buildReq(EsBasePageQueryBO<T> queryBO) {
        return new SearchScrollRequest().scroll(queryBO.getScrollTime()).scrollId(queryBO.getScrollId());
    }

    default EsBaseSearchDTO<T> buildResp(Exception ex) {
        throw new EsOptException(ex);
    }

    EsBaseSearchDTO<T> buildResp(SearchResponse response);

}
