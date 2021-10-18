package com.elasticsearch.demo.model.bo.bulk;

import com.elasticsearch.demo.enums.BulkOperationType;
import com.elasticsearch.demo.model.entiy.EsBaseEntity;
import lombok.Data;
import org.elasticsearch.action.bulk.BulkRequest;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
@Data
public abstract class BulkOperationBO<T extends EsBaseEntity> {

    private BulkOperationType operationType;

    private String index;

    private T t;

    public abstract void addRequest(BulkRequest bulkRequest);
}
