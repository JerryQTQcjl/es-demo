package com.elasticsearch.demo.factory;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author jerry chan
 * @date 2021/10/13
 */
public class EsClientFactory {

    public static RestHighLevelClient getInstance() {
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost("192.168.85.2", 9200, "http"),
                new HttpHost("192.168.85.3", 9200, "http"),
                new HttpHost("192.168.85.4", 9200, "http")
        ));
    }
}
