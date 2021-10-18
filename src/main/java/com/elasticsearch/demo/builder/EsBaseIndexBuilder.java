package com.elasticsearch.demo.builder;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.elasticsearch.demo.model.entiy.EsBaseEntity;
import org.elasticsearch.action.index.IndexRequest;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public interface EsBaseIndexBuilder<T extends EsBaseEntity> {

    default IndexRequest buildReq(T t) {
        return new IndexRequest().source(BeanUtil.beanToMap(t))
                .id(StrUtil.toString(t.getId()));
    }

}
