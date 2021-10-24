package com.elasticsearch.demo.builder.clue;

import cn.hutool.core.bean.BeanUtil;
import com.elasticsearch.demo.builder.EsBaseScrollBuilder;
import com.elasticsearch.demo.exception.EsOptException;
import com.elasticsearch.demo.model.bo.page.EsBasePageQueryBO;
import com.elasticsearch.demo.model.dto.EsBaseSearchDTO;
import com.elasticsearch.demo.model.dto.EsScrollSearchDTO;
import com.elasticsearch.demo.model.entiy.ClueDetail;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.search.SearchHits;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public class EsClueDetailScrollQueryBuilder implements EsBaseScrollBuilder<ClueDetail> {

    protected ThreadLocal<EsBaseSearchDTO<ClueDetail>> pageInfo = new ThreadLocal<>();

    @Override
    public SearchScrollRequest buildReq(EsBasePageQueryBO<ClueDetail> queryBO) {
        pageInfo.set(new EsScrollSearchDTO<ClueDetail>().setScrollTime(queryBO.getScrollTime()).setScrollId(queryBO.getScrollId()));
        return new SearchScrollRequest().scroll(queryBO.getScrollTime()).scrollId(queryBO.getScrollId());
    }

    @Override
    public EsBaseSearchDTO<ClueDetail> buildResp(SearchResponse response) {
        try {
            EsScrollSearchDTO<ClueDetail> esBaseSearchDTO = (EsScrollSearchDTO<ClueDetail>) pageInfo.get();
            SearchHits hits = response.getHits();
            esBaseSearchDTO.setScrollId(response.getScrollId());
            esBaseSearchDTO.setDataList(Arrays.stream(hits.getHits())
                    .map(hit -> BeanUtil.toBean(hit, ClueDetail.class))
                    .collect(Collectors.toList()));
            return esBaseSearchDTO;
        } finally {
            pageInfo.remove();
        }
    }

    @Override
    public EsBaseSearchDTO<ClueDetail> buildResp(Exception ex) {
        pageInfo.remove();
        throw new EsOptException(ex);
    }

    public static EsClueDetailScrollQueryBuilder getInstance() {
        return EsClueDetailScrollQueryBuilder.Singleton.INSTANCE.esClueDetailScrollQueryBuilder;
    }

    private enum Singleton {
        INSTANCE;
        private EsClueDetailScrollQueryBuilder esClueDetailScrollQueryBuilder = new EsClueDetailScrollQueryBuilder();
    }
}
