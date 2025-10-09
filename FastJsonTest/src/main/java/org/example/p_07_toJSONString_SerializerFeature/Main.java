package org.example.p_07_toJSONString_SerializerFeature;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-31 9:47
 * @Version v2.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * SerializerFeature属性
 *
 *      名称                                  含义
 *      QuoteFieldNames                     输出key时是否使用双引号,默认为true
 *      UseSingleQuotes                     使用单引号而不是双引号,默认为false
 *      WriteMapNullValue                   是否输出值为null的字段,默认为false
 *      WriteEnumUsingToString              Enum输出调用 toString() 方法,默认为false
 *      WriteEnumUsingName                  Enum输出调用 name() 方法,默认为true
 *      UseISO8601DateFormat                Date使用ISO8601格式输出，默认为false
 *      WriteNullListAsEmpty                List字段如果为null,输出为[],而非null
 *      WriteNullStringAsEmpty              字符类型字段如果为null,输出为”“,而非null
 *      WriteNullNumberAsZero               数值字段如果为null,输出为0,而非null
 *      WriteNullBooleanAsFalse             Boolean字段如果为null,输出为false,而非null
 *      SkipTransientField                  如果是true，类中的Get方法对应的Field是transient，序列化时将会被忽略。默认为true
 *      SortField                           按字段名称排序后输出。默认为true
 *      WriteTabAsSpecial                   把\t做转义输出，默认为false   不推荐
 *      PrettyFormat                        结果是否格式化,默认为false
 *      WriteClassName                      序列化时写入类型信息，默认为false。反序列化是需用到
 *      DisableCircularReferenceDetect      消除对同一对象循环引用的问题，默认为false
 *      WriteSlashAsSpecial                 对斜杠’/’进行转义
 *      BrowserCompatible                   将中文都会序列化为 \ uXXXX格式，字节数会多一些，但是能兼容IE 6，默认为false
 *      WriteDateUseDateFormat              全局修改日期格式,默认为false。JSON.DEFFAULT_DATE_FORMAT = “yyyy-MM-dd”;JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
 *      DisableCheckSpecialChar             一个对象的字符串属性中如果有特殊字符如双引号，将会在转成json时带有反斜杠转移符。如果不需要转义，可以使用这个属性。默认为false
 *      NotWriteRootClassName               打开WriteClassName的同时，不输出根对象的类型信息
 *      BeanToArray                         将对象转为array输出
 *      WriteNonStringKeyAsString           将Map中的非String类型的Key当做String类型输出
 *      NotWriteDefaultValue                当字段的值为缺省值时，不输出，这个能节省序列化后结果的大小
 *      BrowserSecure                       浏览器安全，将会’<’ ‘>’ ‘(’ ')'字符做转义输出
 *      IgnoreNonFieldGetter                含义
 *
 * —————————————————————————————————————————————————————————————————————————————————————————————————————
 */
public class Main {

    private static Word word;

    private static void init() {
        word = new Word();
        word.setA("a");
        word.setB(2);
        word.setC(true);
        word.setD("d");
        word.setE("");
        word.setF(null);
        word.setDate(new Date());

        List<User> list = new ArrayList<User>();
        User user1 = new User();
        user1.setId(1);
        user1.setOld("11");
        user1.setName("用户1");
        user1.setAdd("北京");
        User user2 = new User();
        user2.setId(2);
        user2.setOld("22");
        user2.setName("用户2");
        user2.setAdd("上海");
        User user3 = new User();
        user3.setId(3);
        user3.setOld("33");
        user3.setName("用户3");
        user3.setAdd("广州");

        list.add(user3);
        list.add(user2);
        list.add(null);
        list.add(user1);

        word.setList(list);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mapa", "mapa");
        map.put("mapo", "mapo");
        map.put("mapz", "mapz");
        map.put("user1", user1);
        map.put("user3", user3);
        map.put("user4", null);
        map.put("list", list);
        word.setMap(map);
    }

    public static void main(String[] args) {
        init();
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
//        test9();
//        test10();
//        test11();
//        test12();
//        test13();
//        test14();
//        test15();
//        test16();
//        test17();
//        test18();
//        test19();
//        test20();
//        test21();
//        test22();
//        test23();
//        test24();
        test25();
    }

    /**
     * QuoteFieldNames    输出key时是否使用双引号,默认为true
     */
    public static void test1() {
        // 注意：有默认的 配置 DEFAULT_GENERATE_FEATURE
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置QuoteFieldNames后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.QuoteFieldNames));
    }

    /**
     * UseSingleQuotes    使用单引号而不是双引号,默认为false
     */
    public static void test2() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置UseSingleQuotes后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.UseSingleQuotes));
    }

    /**
     * WriteMapNullValue    是否输出值为null的字段,默认为false
     */
    public static void test3() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 WriteMapNullValue 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteMapNullValue));
    }

    /**
     * WriteEnumUsingToString    Enum输出调用 toString 方法,默认为false
     */
    public static void test4() {
        Map<String, Fruit> fruitMap = new HashMap<>();
        fruitMap.put("1", Fruit.Apple);
        fruitMap.put("2", Fruit.Orange);
        fruitMap.put("3", Fruit.WaterMelon);
        System.out.println(JSONObject.toJSONString(fruitMap));
        System.out.println("设置 WriteEnumUsingToString 后：");
        System.out.println(JSONObject.toJSONString(fruitMap, SerializerFeature.WriteEnumUsingToString));

        System.out.println(Fruit.Apple.name());
        System.out.println(Fruit.Apple.ordinal());
    }

    /**
     * WriteEnumUsingName    Enum输出调用 name() 方法,默认为true
     */
    public static void test5() {
        Map<String, Fruit> fruitMap = new HashMap<>();
        fruitMap.put("1", Fruit.Apple);
        fruitMap.put("2", Fruit.Orange);
        fruitMap.put("3", Fruit.WaterMelon);
        System.out.println(JSONObject.toJSONString(fruitMap));
        System.out.println("设置 WriteEnumUsingName 后：");
        System.out.println(JSONObject.toJSONString(fruitMap, SerializerFeature.WriteEnumUsingName));
    }

    /**
     * UseISO8601DateFormat    Date使用ISO8601格式输出，默认为false
     */
    public static void test6() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 UseISO8601DateFormat 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.UseISO8601DateFormat));
    }

    /**
     * WriteNullListAsEmpty    List字段如果为null,输出为[],而非null
     */
    public static void test7() {
        word.setList(null);
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 WriteNullListAsEmpty 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteNullListAsEmpty));
    }

    /**
     * WriteNullStringAsEmpty    字符类型字段如果为null,输出为”“,而非null
     */
    public static void test8() {
        word.setA(null);
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 WriteNullStringAsEmpty 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteNullStringAsEmpty));
    }

    /**
     * WriteNullNumberAsZero    数值字段如果为null,输出为0,而非null
     */
    public static void test9() {
        word.setH(null);
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 WriteNullNumberAsZero 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteNullNumberAsZero));
    }

    /**
     * WriteNullBooleanAsFalse    Boolean字段如果为null,输出为false,而非null
     */
    public static void test10() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 WriteNullBooleanAsFalse 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteNullBooleanAsFalse));
    }

    /**
     * SkipTransientField    如果是true，类中的Get方法对应的Field是transient，序列化时将会被忽略。默认为true
     */
    public static void test11() {
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        class Test {
            private transient String abc;
            private String def;
        }

        System.out.println(JSONObject.toJSONString(new Test("abc", "def")));
        System.out.println("设置 SkipTransientField 后：");
        System.out.println(JSONObject.toJSONString(new Test("abc", "def"), SerializerFeature.SkipTransientField));
    }

    /**
     * SortField    按字段名称排序后输出。默认为true
     */
    public static void test12() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 SortField 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.SortField));
    }

    /**
     * WriteTabAsSpecial    把\t做转义输出，默认为false   不推荐
     */
    public static void test13() {
        word.setA("slfkjsldfkj\t\t\\tsdfsdfsf");
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 WriteTabAsSpecial 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteTabAsSpecial));
    }

    /**
     * PrettyFormat    结果是否格式化,默认为false
     */
    public static void test14() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 PrettyFormat 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.PrettyFormat));
    }

    /**
     * WriteClassName    序列化时写入类型信息，默认为false。反序列化是需用到
     */
    public static void test15() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 WriteClassName 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteClassName));
    }

    /**
     * DisableCircularReferenceDetect      消除对同一对象循环引用的问题，默认为false
     */
    public static void test16() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 DisableCircularReferenceDetect 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.DisableCircularReferenceDetect));
    }

    /**
     * WriteSlashAsSpecial    对斜杠’/’进行转义
     */
    public static void test17() {
        word.setA("slfkjsldfkj/t/tsdfsdfsf");
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 WriteSlashAsSpecial 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteSlashAsSpecial));
    }

    /**
     * BrowserCompatible    将中文都会序列化为 \ uXXXX格式，字节数会多一些，但是能兼容IE 6，默认为false
     */
    public static void test18() {
        word.setA("中文中文");
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 BrowserCompatible 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.BrowserCompatible));
    }

    /**
     * WriteDateUseDateFormat    全局修改日期格式,默认为false。JSON.DEFFAULT_DATE_FORMAT = “yyyy-MM-dd”;
     * JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
     */
    public static void test19() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 WriteDateUseDateFormat 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteDateUseDateFormat));
    }

    /**
     * DisableCheckSpecialChar    一个对象的字符串属性中如果有特殊字符如双引号，将会在转成json时带有反斜杠转移符。如果不需要转义，可以使用这个属性。默认为false
     */
    public static void test20() {
        word.setA("中文\"\"");
        System.out.println(word.getA());
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 DisableCheckSpecialChar 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.DisableCheckSpecialChar));
    }

    /**
     * NotWriteRootClassName    打开WriteClassName的同时，不输出根对象的类型信息
     */
    public static void test21() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 WriteClassName 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteClassName));
        System.out.println("设置 WriteClassName NotWriteRootClassName 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteClassName, SerializerFeature.NotWriteRootClassName));
    }

    /**
     * BeanToArray    将对象转为array输出
     */
    public static void test22() {
        word.setMap(null);
        word.setList(null);
        System.out.println(JSON.toJSONString(word));
        System.out.println(JSON.toJSONString(word, SerializerFeature.BeanToArray));
    }
    /**
     * WriteNonStringKeyAsString           将Map中的非String类型的Key当做String类型输出
     */
    public static void test23() {
        Map<Object, Object> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", "2");
        map.put("3", true);
        map.put(4, true);
        System.out.println(JSONObject.toJSONString(map));
        System.out.println("设置 WriteNonStringKeyAsString 后：");
        System.out.println(JSONObject.toJSONString(map, SerializerFeature.WriteNonStringKeyAsString));
    }

    /**
     * NotWriteDefaultValue                当字段的值为缺省值时，不输出，这个能节省序列化后结果的大小
     */
    public static void test24() {
        word.setB(0);
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 NotWriteDefaultValue 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.NotWriteDefaultValue));
    }

    /**
     * BrowserSecure    浏览器安全，将会’<’ ‘>’ ‘(’ ')'字符做转义输出
     */
    public static void test25() {
        word.setA("<>()");
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置 BrowserSecure 后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.BrowserSecure));
    }
}
