package com.elasticsearch.demo.model.entiy;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
@Accessors(chain = true)
@Data
public abstract class EsBaseEntity implements Serializable {
    private static final long serialVersionUID = 3926856195189464089L;

    protected Long id;
}
