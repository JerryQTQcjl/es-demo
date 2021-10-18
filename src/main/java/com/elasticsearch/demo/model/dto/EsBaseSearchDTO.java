package com.elasticsearch.demo.model.dto;

import java.util.List;
import java.util.Map;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public interface EsBaseSearchDTO<T> {

    default int getTotal() {
        throw new UnsupportedOperationException("esBaseSearchDTO unSupport getTotal!");
    }

    List<T> getDataList();

    default int getCurPage() {
        throw new UnsupportedOperationException("esBaseSearchDTO unSupport getCurPage!");
    }

    default String getScrollId() {
        throw new UnsupportedOperationException("esBaseSearchDTO unSupport getScrollId!");
    }

    default Map<String, Object> getSearchAfterParam() {
        throw new UnsupportedOperationException("esBaseSearchDTO unSupport getSearchAfterParam!");
    }
}
