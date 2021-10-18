package com.elasticsearch.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
@Getter
@AllArgsConstructor
public enum BulkOperationType {

    INSERT,
    UPDATE,
    DELETE,
    SEARCH;

//    private BiConsumer<BulkRequest, ? extends EsBaseEntity> addFunction;
}
