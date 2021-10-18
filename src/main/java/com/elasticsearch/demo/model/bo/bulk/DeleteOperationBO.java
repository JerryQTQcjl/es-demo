package com.elasticsearch.demo.model.bo.bulk;

import cn.hutool.core.util.StrUtil;
import com.elasticsearch.demo.model.entiy.EsBaseEntity;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public class DeleteOperationBO<T extends EsBaseEntity> extends BulkOperationBO<T> {

    @Override
    public void addRequest(BulkRequest bulkRequest) {
        Long id = getT().getId();
        bulkRequest.add(new DeleteRequest().index(getIndex()).id(StrUtil.toString(id)));
    }
}
