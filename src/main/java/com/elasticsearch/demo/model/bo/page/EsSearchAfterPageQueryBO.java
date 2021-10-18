package com.elasticsearch.demo.model.bo.page;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author jerry chan
 * @date 2021/10/14
 */
@Data
@Accessors(chain = true)
public class EsSearchAfterPageQueryBO<T> implements EsBasePageQueryBO<T> {

    private T t;
    private Map<String, Object> searchAfterParam;
}
