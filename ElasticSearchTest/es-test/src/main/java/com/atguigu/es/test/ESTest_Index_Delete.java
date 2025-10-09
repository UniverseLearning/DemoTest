package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

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
public class ESTest_Index_Delete {
    public static void main(String[] args) throws IOException {

        // 创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("ky141", 9200, "http"))
        );

        // 查询索引
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println(response.isAcknowledged());

        // 关闭ES客户端
        esClient.close();


    }
}
