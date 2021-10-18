package com.elasticsearch.demo.builder.clue;

import com.elasticsearch.demo.builder.EsBaseQueryBuilder;
import com.elasticsearch.demo.model.bo.page.EsBasePageQueryBO;
import com.elasticsearch.demo.model.dto.EsBaseSearchDTO;
import com.elasticsearch.demo.model.entiy.ClueDetail;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Optional;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public class EsClueDetailPageQueryBuilder implements EsBaseQueryBuilder<ClueDetail> {

    @Override
    public SearchSourceBuilder buildReq(EsBasePageQueryBO<ClueDetail> queryBO) {
        ClueDetail t = queryBO.getT();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Optional.ofNullable(t.getId()).ifPresent((val) -> boolQueryBuilder.must(QueryBuilders.matchQuery("id", val)));
        Optional.ofNullable(t.getName()).ifPresent((val) -> boolQueryBuilder.must(QueryBuilders.matchQuery("name", val)));
        Optional.ofNullable(t.getCompany()).ifPresent((val) -> boolQueryBuilder.must(QueryBuilders.matchQuery("company", val)));
        Optional.ofNullable(t.getStatus()).ifPresent((val) -> boolQueryBuilder.must(QueryBuilders.termQuery("status", val)));
        //...
        return new SearchSourceBuilder().from(queryBO.getFrom())
                .size(queryBO.getSize())
                .query(boolQueryBuilder);
    }

    @Override
    public EsBaseSearchDTO<ClueDetail> buildResp(SearchResponse response) {
        return null;
    }

    public static EsClueDetailPageQueryBuilder getInstance() {
        return Singleton.INSTANCE.esClueDetailPageQueryBuilder;
    }

    private enum Singleton {
        INSTANCE;
        private EsClueDetailPageQueryBuilder esClueDetailPageQueryBuilder = new EsClueDetailPageQueryBuilder();
    }
}
