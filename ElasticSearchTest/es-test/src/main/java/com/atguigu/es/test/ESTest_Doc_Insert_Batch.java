package com.atguigu.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
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
public class ESTest_Doc_Insert_Batch {
    public static void main(String[] args) throws IOException {

        // 创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("ky141", 9200, "http"))
        );

        // 批量插入数据
        BulkRequest request = new BulkRequest();

        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "zhang3", "age", 30, "sex", "F"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "li4", "age", 40, "sex", "F"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "wang5", "age", 32, "sex", "M"));
        request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON, "name", "zhao6", "age", 33, "sex", "F"));
        request.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON, "name", "zhao1", "age", 35, "sex", "M"));
        request.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON, "name", "zhao2", "age", 31, "sex", "F"));
        request.add(new IndexRequest().index("user").id("1007").source(XContentType.JSON, "name", "zhao3", "age", 44, "sex", "M"));
        request.add(new IndexRequest().index("user").id("1008").source(XContentType.JSON, "name", "zhao4", "age", 55, "sex", "F"));
        request.add(new IndexRequest().index("user").id("1009").source(XContentType.JSON, "name", "zhao5", "age", 53, "sex", "M"));
        request.add(new IndexRequest().index("user").id("1010").source(XContentType.JSON, "name", "zhao666", "age", 26, "sex", "F"));

        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(response.getItems());


        // 关闭ES客户端
        esClient.close();


    }
}
