package com.atguigu.kafka.test.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2025-10-22 16:21
 * @Version v2.0
 */
public class KafkaProducerCallbackTest {
    // 异步发送
//    public static void main(String[] args) {
//
//        Map<String, Object> configMap = new HashMap<>();
//        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
//        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//
//        KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);
//
//        for (int i = 0; i < 10; i++) {
//            ProducerRecord<String, String> record = new ProducerRecord<>(
//                    "test", "keyCallBack" + i, "valueCallBack" + i
//            );
//            producer.send(record, new Callback() {
//                @Override
//                public void onCompletion(RecordMetadata metadata, Exception exception) {
//                    System.out.println("发送成功");
//                }
//            });
//        }
//
//
//        producer.close();
//
//    }
    // 同步发送
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);

        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(
                    "test", "keyCallBackSync" + i, "valueCallBackSync" + i
            );
            Future<RecordMetadata> send = producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println("发送成功！！");
                }
            });
            System.out.println("发送完成");
            RecordMetadata metadata = send.get();

        }


        producer.close();

    }
}
