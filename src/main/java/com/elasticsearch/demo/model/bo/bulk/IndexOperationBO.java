package com.elasticsearch.demo.model.bo.bulk;

import com.elasticsearch.demo.builder.EsBaseIndexBuilder;
import com.elasticsearch.demo.model.entiy.EsBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.elasticsearch.action.bulk.BulkRequest;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class IndexOperationBO<T extends EsBaseEntity> extends BulkOperationBO<T> {

    private EsBaseIndexBuilder<T> indexBuilder;

    @Override
    public void addRequest(BulkRequest bulkRequest) {
        bulkRequest.add(indexBuilder.buildReq(getT()).index(getIndex()));
    }
}
