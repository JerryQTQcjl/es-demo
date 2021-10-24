package com.elasticsearch.demo.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author jerry chan
 * @date 2021/10/23
 */
@Data
@Accessors(chain = true)
public class EsScrollSearchDTO<T> implements EsBaseSearchDTO<T> {

    private String scrollTime;
    private String scrollId;
    private List<T> dataList;
}
