package com.elasticsearch.demo.model.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public interface EsBaseSearchDTO<T> {

    default int getTotal() {
        throw new UnsupportedOperationException("esBaseSearchDTO unSupport getTotal!");
    }

    default List<T> getDataList() {
        return new ArrayList<>(0);
    }

    default int getCurPage() {
        throw new UnsupportedOperationException("esBaseSearchDTO unSupport getCurPage!");
    }

    default int getPageSize() {
        throw new UnsupportedOperationException("esBaseSearchDTO unSupport getPageSize!");
    }

    default String getScrollId() {
        throw new UnsupportedOperationException("esBaseSearchDTO unSupport getScrollId!");
    }

    default LinkedHashMap<String, String> getSortParam() {
        throw new UnsupportedOperationException("esBaseSearchDTO unSupport getSortParam!");
    }

    default LinkedHashMap<String, Object> getSearchAfterParam() {
        throw new UnsupportedOperationException("esBaseSearchDTO unSupport getSearchAfterParam!");
    }
}
