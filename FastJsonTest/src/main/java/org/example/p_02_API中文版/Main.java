package org.example.p_02_API中文版;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-28 14:59
 * @Version v2.0
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 序列化：
 *     // 将Java对象序列化为JSON字符串，支持各种各种Java基本类型和JavaBean
 *     public static String toJSONString(Object object, SerializerFeature... features);
 *
 *     // 将Java对象序列化为JSON字符串，返回JSON字符串的utf-8 bytes
 *     public static byte[] toJSONBytes(Object object, SerializerFeature... features);
 *
 *     // 将Java对象序列化为JSON字符串，写入到Writer中
 *     public static void writeJSONString(Writer writer, Object object, SerializerFeature... features);
 *
 *     // 将Java对象序列化为JSON字符串，按UTF-8编码写入到OutputStream中
 *     public static final int writeJSONString(OutputStream os, Object object, SerializerFeature... features);
 *
 * 反序列化：
 *     // 将JSON字符串反序列化为JavaBean
 *     public static <T> T parseObject(String jsonStr, Class<T> clazz, Feature... features);
 *
 *     // 将JSON字符串反序列化为JavaBean  // UTF-8格式的JSON字符串
 *     public static <T> T parseObject(byte[] jsonBytes, Class<T> clazz, Feature... features);
 *
 *     // 将JSON字符串反序列化为泛型类型的JavaBean
 *     public static <T> T parseObject(String text, TypeReference<T> type, Feature... features);
 *
 *     // 将JSON字符串反序列为JSONObject
 *     public static JSONObject parseObject(String text);
 */
public class Main {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }



    public static void test1() {
        // 解析树
        String jsonStr = "{'id': 1, 'name': '张三'}";
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        System.out.println(jsonObj);
    }
    public static void test2() {
        // 解析POJO
        String jsonStr = "{'id': 1, 'name': '张三'}";
        Model model = JSON.parseObject(jsonStr, Model.class);
        System.out.println(model);
    }
    public static void test3() {
        // 解析POJO泛型
        String jsonStr = "[{'id': 1, 'name': '张三'}, {'id': 2, 'name': '李四'}]";
        Type type = new TypeReference<List<Model>>() {}.getType();
        List<Model> list = JSON.parseObject(jsonStr, type);
        System.out.println(list);
    }
    public static void test4() {
        // 将POJO转换为json字符串
        Model model = new Model(3, "王五");
        String jsonStr = JSON.toJSONString(model);
        System.out.println(jsonStr);
    }
    public static void test5() {
        // 将POJO转换为json字节
        Model model = new Model(4, "赵六");;
        byte[] jsonBytes = JSON.toJSONBytes(model);
        System.out.println(jsonBytes);
    }
    @SneakyThrows
    public static void test6() {
        // 将POJO作为json字符串写入OutputStream
        Model model = new Model(4, "赵六");
        OutputStream os = new FileOutputStream(new File("E:\\1.txt"));
        JSON.writeJSONString(os, model);
    }
    @SneakyThrows
    public static void test7() {
        // 将POJO作为json字符串写入Writer
        Model model = new Model(4, "赵六");
        Writer writer = new FileWriter("E:\\2.txt");
        JSON.writeJSONString(writer, model);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Model {

    private Integer id;

    private String name;
}
