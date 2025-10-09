package org.example.p_03_JSONField;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-29 9:17
 * @Version v2.0
 */

/**
 * 1. JSONField 介绍
 * 注意：1、若属性是私有的，必须有set*方法。否则无法反序列化。
 */

import com.alibaba.fastjson.JSON;
import lombok.ToString;

/**
 *     // 配置序列化和反序列化的顺序，1.1.42版本之后才支持
 *     int ordinal() default 0;
 *
 *      // 指定字段的名称
 *     String name() default "";
 *
 *     // 指定字段的格式，对日期格式有用
 *     String format() default "";
 *
 *     // 是否序列化
 *     boolean serialize() default true;
 *
 *     // 是否反序列化
 *     boolean deserialize() default true;
 */
public class Class1 {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
    }

    /**
     * 私有 get set 序列化
     */
    public static void test1() {
        Model1 m1 = new Model1(1);
        System.out.println(JSON.toJSONString(m1));
    }

    /**
     * 私有 get set 反序列化
     */
    public static void test2() {
        String jsonStr = "{'id': 1}";
        System.out.println(JSON.parseObject(jsonStr, Model1.class));
    }

    /**
     * 私有 set 序列化
     */
    public static void test3() {
        Model2 m2 = new Model2(2);
        System.out.println(JSON.toJSONString(m2));
    }

    /**
     * 私有 set 反序列化
     */
    public static void test4() {
        String jsonStr = "{'id': 2}";
        System.out.println(JSON.parseObject(jsonStr, Model2.class));
    }

    /**
     * 私有 get 序列化
     */
    public static void test5() {
        Model3 m3 = new Model3(3);
        System.out.println(JSON.toJSONString(m3));
    }

    /**
     * 私有 get 反序列化
     */
    public static void test6() {
        String jsonStr = "{'id': 3}";
        System.out.println(JSON.parseObject(jsonStr, Model3.class));
    }

    /**
     * 公有 序列化
     */
    public static void test7() {
        Model4 m4 = new Model4(4);
        System.out.println(JSON.toJSONString(m4));
    }

    /**
     * 公有 反序列化
     */
    public static void test8() {
        String jsonStr = "{'id': 4}";
        System.out.println(JSON.parseObject(jsonStr, Model4.class));
    }




}
@ToString
class Model1 {
    public Model1() {
    }

    public Model1(Integer id) {
        this.id = id;
    }

    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
@ToString
class Model2 {
    private Integer id;
    public Model2() {
    }

    public Model2(Integer id) {
        this.id = id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
@ToString
class Model3 {
    private Integer id;
    public Model3() {
    }

    public Model3(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
}
@ToString
class Model4 {
    public Model4() {
    }

    public Model4(Integer id) {
        this.id = id;
    }
    public Integer id;
}