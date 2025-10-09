package com.atguigu.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

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
public class ESTest_Doc_Search {
    public static void main(String[] args) throws IOException {

        // 创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("ky141", 9200, "http"))
        );

        // 1. 查询全部数据
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }


        // 2.条件查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        request.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age", 44)));
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 3.分页查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        builder.from(3);
//        builder.size(3);
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 4.查询排序
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        builder.sort("age", SortOrder.ASC);
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 5.过滤字段
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        builder.sort("age", SortOrder.ASC);
//        builder.fetchSource(new String[]{"age", "name"}, new String[]{});
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 6.组合查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        BoolQueryBuilder booledQuery = QueryBuilders.boolQuery();
//        booledQuery.must(QueryBuilders.matchQuery("age", 30));
//        booledQuery.must(QueryBuilders.matchQuery("sex", "F"));
//
//
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(booledQuery);
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 7.范围查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");
//        rangeQueryBuilder.gte(30);
//        rangeQueryBuilder.lte(50);
//
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(rangeQueryBuilder);
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 8.模糊查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "zhao").fuzziness(Fuzziness.ONE);
//
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(fuzzyQueryBuilder);
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }
        
        // 9.高亮查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "zhao6");
//
//        SearchSourceBuilder builder = new SearchSourceBuilder().query(termQueryBuilder);
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.preTags("<font color='red'>");
//        highlightBuilder.postTags("</font>");
//        highlightBuilder.field("name");
//
//        builder.highlighter(highlightBuilder);
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//        System.out.println(response.toString());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 10.聚合查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        AggregationBuilder buildersub = AggregationBuilders.max("max_age").field("age");
//
//        SearchSourceBuilder builder = new SearchSourceBuilder().aggregation(buildersub);
//        request.source(builder);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//        System.out.println(response.toString());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 11.分组查询
        SearchRequest request = new SearchRequest();
        request.indices("user");

        AggregationBuilder buildersub = AggregationBuilders.terms("ageGroup").field("age");

        SearchSourceBuilder builder = new SearchSourceBuilder().aggregation(buildersub);
        request.source(builder);

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());
        System.out.println(response.toString());

        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }




        // 关闭ES客户端
        esClient.close();


    }
}
