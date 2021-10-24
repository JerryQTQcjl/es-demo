package com.elasticsearch.demo.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;

/**
 * @author jerry chan
 * @date 2021/10/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class EsAfterSearchDTO<T> extends EsPageSearchDTO<T> {

    private LinkedHashMap<String,String> sortParam;
    private LinkedHashMap<String, Object> searchAfterParam;
}
