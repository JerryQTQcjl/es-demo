package com.elasticsearch.demo.model.bo.page;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jerry chan
 * @date 2021/10/14
 */
@Data
@Accessors(chain = true)
public class EsScrollPageQueryBO<T> implements EsBasePageQueryBO<T> {

    private T t;
    private int from;
    private int size;
    private String scrollId;
    private String scrollTime;
}
