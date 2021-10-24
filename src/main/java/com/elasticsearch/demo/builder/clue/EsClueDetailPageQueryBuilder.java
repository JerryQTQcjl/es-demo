package com.elasticsearch.demo.builder.clue;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.elasticsearch.demo.builder.EsBaseQueryBuilder;
import com.elasticsearch.demo.exception.EsOptException;
import com.elasticsearch.demo.model.bo.page.EsBasePageQueryBO;
import com.elasticsearch.demo.model.dto.EsBaseSearchDTO;
import com.elasticsearch.demo.model.dto.EsPageSearchDTO;
import com.elasticsearch.demo.model.dto.EsScrollSearchDTO;
import com.elasticsearch.demo.model.entiy.ClueDetail;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public class EsClueDetailPageQueryBuilder implements EsBaseQueryBuilder<ClueDetail> {

    protected ThreadLocal<EsBaseSearchDTO<ClueDetail>> pageInfo = new ThreadLocal<>();

    @Override
    public SearchSourceBuilder buildReq(EsBasePageQueryBO<ClueDetail> queryBO) {
        BoolQueryBuilder boolQueryBuilder = buildCriteria(queryBO.getT());
        if (StrUtil.isNotBlank(queryBO.getScrollTime())) {
            pageInfo.set(new EsScrollSearchDTO<ClueDetail>()
                    .setScrollTime(queryBO.getScrollTime()));
        } else {
            pageInfo.set(new EsPageSearchDTO<ClueDetail>()
                    .setCurPage((queryBO.getFrom() / queryBO.getSize()) + 1)
                    .setPageSize(queryBO.getSize()));
        }

        return new SearchSourceBuilder().from(queryBO.getFrom())
                .size(queryBO.getSize())
                .query(boolQueryBuilder);
    }

    protected BoolQueryBuilder buildCriteria(ClueDetail t) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Optional.ofNullable(t).ifPresent(_t -> {
            Optional.ofNullable(_t.getId()).ifPresent((val) -> boolQueryBuilder.must(QueryBuilders.matchQuery("id", val)));
            Optional.ofNullable(_t.getName()).ifPresent((val) -> boolQueryBuilder.must(QueryBuilders.matchQuery("name", val)));
            Optional.ofNullable(_t.getCompany()).ifPresent((val) -> boolQueryBuilder.must(QueryBuilders.matchQuery("company", val)));
            Optional.ofNullable(_t.getStatus()).ifPresent((val) -> boolQueryBuilder.must(QueryBuilders.termQuery("status", val)));
            //...
        });
        return boolQueryBuilder;
    }

    @Override
    public EsBaseSearchDTO<ClueDetail> buildResp(Exception ex) {
        pageInfo.remove();
        throw new EsOptException(ex);
    }

    @Override
    public EsBaseSearchDTO<ClueDetail> buildResp(SearchResponse response) {
        try {
            SearchHits searchHits = response.getHits();
            List<ClueDetail> clueDetailList = Arrays.stream(searchHits.getHits())
                    .map(hit -> BeanUtil.toBean(hit.getSourceAsMap(), ClueDetail.class))
                    .collect(Collectors.toList());
            EsBaseSearchDTO<ClueDetail> esBaseSearchDTO = pageInfo.get();

            if (StrUtil.isNotBlank(response.getScrollId())) {
                EsScrollSearchDTO<ClueDetail> esScrollSearchDTO = (EsScrollSearchDTO<ClueDetail>) esBaseSearchDTO;
                esScrollSearchDTO.setScrollId(response.getScrollId());
                esScrollSearchDTO.setDataList(clueDetailList);
            } else {
                EsPageSearchDTO<ClueDetail> esPageSearchDTO = (EsPageSearchDTO<ClueDetail>) esBaseSearchDTO;
                esPageSearchDTO.setTotal((int) searchHits.getTotalHits().value);
                esPageSearchDTO.setDataList(clueDetailList);
            }
            return esBaseSearchDTO;
        } finally {
            pageInfo.remove();
        }
    }

    public static EsClueDetailPageQueryBuilder getInstance() {
        return Singleton.INSTANCE.esClueDetailPageQueryBuilder;
    }

    private enum Singleton {
        INSTANCE;
        private EsClueDetailPageQueryBuilder esClueDetailPageQueryBuilder = new EsClueDetailPageQueryBuilder();
    }
}
