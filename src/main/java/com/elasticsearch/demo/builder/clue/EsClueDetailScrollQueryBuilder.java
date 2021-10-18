package com.elasticsearch.demo.builder.clue;

import com.elasticsearch.demo.builder.EsBaseScrollBuilder;
import com.elasticsearch.demo.model.dto.EsBaseSearchDTO;
import com.elasticsearch.demo.model.entiy.ClueDetail;
import org.elasticsearch.action.search.SearchResponse;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public class EsClueDetailScrollQueryBuilder implements EsBaseScrollBuilder<ClueDetail> {

    @Override
    public EsBaseSearchDTO<ClueDetail> buildResp(SearchResponse response) {
        return null;
    }

    public static EsClueDetailScrollQueryBuilder getInstance() {
        return EsClueDetailScrollQueryBuilder.Singleton.INSTANCE.esClueDetailScrollQueryBuilder;
    }

    private enum Singleton {
        INSTANCE;
        private EsClueDetailScrollQueryBuilder esClueDetailScrollQueryBuilder = new EsClueDetailScrollQueryBuilder();
    }
}
