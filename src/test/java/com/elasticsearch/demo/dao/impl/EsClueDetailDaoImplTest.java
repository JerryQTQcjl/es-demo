package com.elasticsearch.demo.dao.impl;

import com.elasticsearch.demo.dao.EsClueDetailDao;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

//    public
}