package com.elasticsearch.demo.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
@Data
@Accessors(chain = true)
public class EsPageSearchDTO<T> implements EsBaseSearchDTO<T> {

    private int total;
    private int curPage;
    private List<T> dataList;

}
