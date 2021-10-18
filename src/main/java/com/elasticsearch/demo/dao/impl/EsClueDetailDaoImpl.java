package com.elasticsearch.demo.dao.impl;

import cn.hutool.core.map.MapUtil;
import com.elasticsearch.demo.dao.EsClueDetailDao;
import com.elasticsearch.demo.model.entiy.ClueDetail;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public class EsClueDetailDaoImpl extends EsBaseDaoImpl<ClueDetail> implements EsClueDetailDao {

    @Override
    public String getIndexName() {
        return "index_clue";
    }

    @Override
    public Map<String, Object> getIndexMapping() {
        return MapUtil.builder(new HashMap<String, Object>()).build();
    }

    @Override
    public Map<String, Object> getIndexSetting() {
        return MapUtil.builder(new HashMap<String, Object>()).build();
    }

}
