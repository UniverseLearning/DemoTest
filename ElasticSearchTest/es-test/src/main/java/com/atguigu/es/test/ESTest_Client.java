package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2025-09-23 13:52
 * @Version v2.0
 */
public class ESTest_Client {
    public static void main(String[] args) throws IOException {

        // 创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("ky141", 9200, "http"))
        );







        // 关闭ES客户端
        esClient.close();


    }
}
