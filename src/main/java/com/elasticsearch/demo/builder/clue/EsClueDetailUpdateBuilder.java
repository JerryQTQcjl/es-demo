package com.elasticsearch.demo.builder.clue;

import com.elasticsearch.demo.builder.EsBaseUpdateBuilder;
import com.elasticsearch.demo.model.entiy.ClueDetail;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public class EsClueDetailUpdateBuilder implements EsBaseUpdateBuilder<ClueDetail> {

    public static EsClueDetailUpdateBuilder getInstance() {
        return EsClueDetailUpdateBuilder.Singleton.INSTANCE.esClueDetailUpdateBuilder;
    }

    private enum Singleton {
        INSTANCE;

        private EsClueDetailUpdateBuilder esClueDetailUpdateBuilder = new EsClueDetailUpdateBuilder();
    }

}
