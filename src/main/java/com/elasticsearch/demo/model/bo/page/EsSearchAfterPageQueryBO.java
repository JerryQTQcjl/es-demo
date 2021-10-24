package com.elasticsearch.demo.model.bo.page;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;

/**
 * @author jerry chan
 * @date 2021/10/14
 */
@Data
@Accessors(chain = true)
public class EsSearchAfterPageQueryBO<T> implements EsBasePageQueryBO<T> {

    private T t;
    private int size;
    private LinkedHashMap<String, String> sortParam;
    private LinkedHashMap<String, Object> searchAfterParam;

    @Override
    public String getScrollId() {
        return null;
    }

    @Override
    public String getScrollTime() {
        return null;
    }
}
