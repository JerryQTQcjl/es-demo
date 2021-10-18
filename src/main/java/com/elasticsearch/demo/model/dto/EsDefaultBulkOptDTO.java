package com.elasticsearch.demo.model.dto;

import org.elasticsearch.action.bulk.BulkResponse;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public class EsDefaultBulkOptDTO implements EsBaseBulkOptDTO {

    private Boolean hasFailures;
    private String buildFailureMessage;
    private long took;

    public EsDefaultBulkOptDTO(BulkResponse bulkResponse) {
        this.hasFailures = bulkResponse.hasFailures();
        this.buildFailureMessage = bulkResponse.buildFailureMessage();
        this.took = bulkResponse.getTook().getMicros();
    }

    @Override
    public boolean hasFailures() {
        return this.hasFailures;
    }

    @Override
    public String buildFailureMessage() {
        return this.buildFailureMessage;
    }

    @Override
    public Long getTook() {
        return this.took;
    }
}
