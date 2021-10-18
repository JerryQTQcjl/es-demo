package com.elasticsearch.demo.builder;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.elasticsearch.demo.model.entiy.EsBaseEntity;
import org.elasticsearch.action.update.UpdateRequest;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public interface EsBaseUpdateBuilder<T extends EsBaseEntity> {

    default UpdateRequest buildReq(T t) {
        return new UpdateRequest().id(StrUtil.toString(t.getId())).doc(BeanUtil.beanToMap(t));
    }

}
