package com.elasticsearch.demo.dao.impl;

import cn.hutool.core.map.MapUtil;
import com.elasticsearch.demo.builder.clue.*;
import com.elasticsearch.demo.dao.EsClueDetailDao;
import com.elasticsearch.demo.model.bo.page.EsNormalPageQueryBO;
import com.elasticsearch.demo.model.bo.page.EsScrollPageQueryBO;
import com.elasticsearch.demo.model.bo.page.EsSearchAfterPageQueryBO;
import com.elasticsearch.demo.model.dto.EsBaseSearchDTO;
import com.elasticsearch.demo.model.entiy.ClueDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedHashMap;

@Slf4j
public class EsClueDetailDaoImplTest {

    private EsClueDetailDao esClueDetailDao;

    @Before
    public void setUp() throws Exception {
        esClueDetailDao = new EsClueDetailDaoImpl();
    }

    @Test
    public void create() {
        esClueDetailDao.create();
    }

    @Test
    public void delete() {
        esClueDetailDao.delete();
    }

    @Test
    public void pageSearch() {
        EsBaseSearchDTO<ClueDetail> esBaseSearchDTO = esClueDetailDao.pageSearch(EsClueDetailPageQueryBuilder.getInstance(),
                new EsNormalPageQueryBO<ClueDetail>()
                        .setFrom(0)
                        .setSize(10)
                        .setT(new ClueDetail().setName("chan")));
        log.info("test pageSearch reponse:{}", esBaseSearchDTO);
    }

    @Test
    public void searchAfter() {
        EsBaseSearchDTO<ClueDetail> esBaseSearchDTO = esClueDetailDao.pageSearch(EsClueDetailAfterSearchBuilder.getInstance(),
                new EsSearchAfterPageQueryBO<ClueDetail>()
                        .setSize(10)
                        .setSortParam((LinkedHashMap<String, String>) MapUtil.builder(new LinkedHashMap<String, String>()).put("id", "ASC").build())
                        .setSearchAfterParam((LinkedHashMap<String, Object>) MapUtil.builder(new LinkedHashMap<String, Object>()).put("id", 2).build())
                        .setT(new ClueDetail().setName("jerry")));
        log.info("test searchAfter reponse:{}", esBaseSearchDTO);
    }

    @Test
    public void scrollSearch() {
        EsBaseSearchDTO<ClueDetail> esBaseSearchDTO = esClueDetailDao.scrollSearch(
                EsClueDetailScrollQueryBuilder.getInstance(),
                EsClueDetailPageQueryBuilder.getInstance(),
                new EsScrollPageQueryBO<ClueDetail>()
                        .setScrollTime("1ms")
                        .setScrollId("FGluY2x1ZGVfY29udGV4dF91dWlkDXF1ZXJ5QW5kRmV0Y2gBFmEwSXhmZE1xVFBHTll5YlJGMDBsTFEAAAAAAAAaFhZyUGh2Q3BVX1JHdXlnc2NsSFhQUEJ3")
                        .setSize(1)
                        .setT(new ClueDetail().setName("jerry"))
        );
        log.info("test searchAfter reponse:{}", esBaseSearchDTO);

    }

    @Test
    public void save() {
        ClueDetail clueDetail = (ClueDetail) new ClueDetail().setName("jerry")
                .setPhone("13500000000")
                .setCountry(100000)
                .setProvince(400000)
                .setCity(123)
                .setRegion(345)
                .setRegionValue("中国 广东省 深圳市")
                .setStatus((byte) 1)
                .setInputMethod((byte) 1)
                .setCorpId(21299L)
                .setCreateTime(new Date())
                .setModifyTime(new Date())
                .setId(2L);
        esClueDetailDao.save(EsClueDetailIndexBuilder.getInstance(), clueDetail);
    }

    @Test
    public void update() {
        ClueDetail clueDetail = (ClueDetail) new ClueDetail()
                .setPhone("13500000001")
                .setId(1L);
        esClueDetailDao.update(EsClueDetailUpdateBuilder.getInstance(), clueDetail);
    }

    @Test
    public void deleteIndex() {
        esClueDetailDao.deleteIndex(1L);
    }
}