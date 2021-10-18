package com.elasticsearch.demo.model.dto;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public interface EsBaseBulkOptDTO {

    boolean hasFailures();

    String buildFailureMessage();

//    void getItems();

    Long getTook();

}
