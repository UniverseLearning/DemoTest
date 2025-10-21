package com.atguigu.bigdata.spark.rdd.operate;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2025-10-13 14:22
 * @Version v2.0
 */
public class Spark21_Operate_JDBC {
    public static void main(String[] args) {
        // 1. 创建 SparkSession
        SparkSession spark = SparkSession.builder()
                .appName("JDBC Example")
                .master("local[2]")
                .getOrCreate();

        // 2. JDBC 配置
        String url = "jdbc:mysql://localhost:3306/ky_new_auth?" +
                "useSSL=false&" +
                "allowPublicKeyRetrieval=true&" +
                "serverTimezone=UTC";
        String user = "edc";
        String password = "lrhealth@edc";
        String dbtable = "(SELECT proj_id, proj_name FROM t_project) as t";  // 子查询需别名

        // 3. 使用 DataFrameReader 读取 JDBC 数据
        Dataset<Row> df = spark.read()
                .format("jdbc")
                .option("url", url)
                .option("dbtable", dbtable)
                .option("user", user)
                .option("password", password)
                .option("numPartitions", 2)
                .option("partitionColumn", "proj_id")  // 按 proj_id 分区
                .option("lowerBound", 1)
                .option("upperBound", 1000)
                .option("fetchSize", 1000)  // 每次取多少条
                .load();

        // 4. 显示结果
        df.show();

        // 5. 可选：转为 JavaRDD（如果必须用 RDD）
        // JavaRDD<Row> rdd = df.javaRDD();

        // 6. 停止 Spark
        spark.stop();
    }
}
