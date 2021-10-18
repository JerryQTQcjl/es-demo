package com.elasticsearch.demo.model.bo.bulk;

import com.elasticsearch.demo.builder.EsBaseUpdateBuilder;
import com.elasticsearch.demo.model.entiy.EsBaseEntity;
import org.elasticsearch.action.bulk.BulkRequest;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public class UpdateOperationBO<T extends EsBaseEntity> extends BulkOperationBO<T> {

    private EsBaseUpdateBuilder<T> updateBuilder;

    @Override
    public void addRequest(BulkRequest bulkRequest) {
        bulkRequest.add(updateBuilder.buildReq(getT()).index(getIndex()));
    }
}
