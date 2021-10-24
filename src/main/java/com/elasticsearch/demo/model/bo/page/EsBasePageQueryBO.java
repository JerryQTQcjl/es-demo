package com.elasticsearch.demo.model.bo.page;

import java.util.LinkedHashMap;

/**
 * @author jerry chan
 * @date 2021/10/14
 */
public interface EsBasePageQueryBO<T> {

    default T getT() {
        throw new UnsupportedOperationException("not support get instance");
    }

    default int getFrom() {
        throw new UnsupportedOperationException("not support get from");
    }

    default int getSize() {
        throw new UnsupportedOperationException("not support get size");
    }

    default String getScrollId() {
        throw new UnsupportedOperationException("not support get scrollId");
    }

    default String getScrollTime() {
        throw new UnsupportedOperationException("not support get scrollTime");
    }

    default LinkedHashMap<String,String> getSortParam(){
        throw new UnsupportedOperationException("not support get getSortParam");
    }

    default LinkedHashMap<String, Object> getSearchAfterParam() {
        throw new UnsupportedOperationException("not support get searchAfterParam");
    }
}
