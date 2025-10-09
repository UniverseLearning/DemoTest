package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

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
public class ESTest_Doc_Delete_Batch {
    public static void main(String[] args) throws IOException {

        // 创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("ky141", 9200, "http"))
        );

        // 批量插入数据
        BulkRequest request = new BulkRequest();

        request.add(new DeleteRequest().index("user").id("1003"));
        request.add(new DeleteRequest().index("user").id("1004"));

        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(response.getItems());


        // 关闭ES客户端
        esClient.close();


    }
}
