package com.elasticsearch.demo.builder.clue;

import com.elasticsearch.demo.builder.EsBaseIndexBuilder;
import com.elasticsearch.demo.model.entiy.ClueDetail;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public class EsClueDetailIndexBuilder implements EsBaseIndexBuilder<ClueDetail> {

    public static EsClueDetailIndexBuilder getInstance() {
        return Singleton.INSTANCE.indexBuilder;
    }

    private enum Singleton {
        INSTANCE;
        private EsClueDetailIndexBuilder indexBuilder = new EsClueDetailIndexBuilder();
    }
}
