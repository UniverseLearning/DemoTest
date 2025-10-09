package org.example.p_09_ParseProcess;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-31 15:34
 * @Version v2.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Fastjson API ParseProcess简介
 *  ParseProcess是编程扩展定制反序列化的接口。fastjson支持如下ParseProcess：
 *
 *      ExtraProcessor 用于处理多余的字段
 *      ExtraTypeProvider 用于处理多余字段时提供类型信息
 */
public class Main {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1() {
        class VO {
            private int id;
            private Map<String, Object> attributes = new HashMap<String, Object>();
            public int getId() { return id; }
            public void setId(int id) { this.id = id;}
            public Map<String, Object> getAttributes() { return attributes;}
        }

        ExtraProcessor processor = new ExtraProcessor() {
            public void processExtra(Object object, String key, Object value) {
                VO vo = (VO) object;
                vo.getAttributes().put(key, value);
            }
        };

        VO vo = JSON.parseObject("{\"id\":123,\"name\":\"abc\"}", VO.class, processor);
        System.out.println(Objects.equal(123, vo.getId()));
        System.out.println(Objects.equal("abc", vo.getAttributes().get("name")));
    }

    public static void test2() {
        class VO {
            private int id;
            private Map<String, Object> attributes = new HashMap<String, Object>();
            public int getId() { return id; }
            public void setId(int id) { this.id = id;}
            public Map<String, Object> getAttributes() { return attributes;}
        }

        class MyExtraProcessor implements ExtraProcessor, ExtraTypeProvider {
            public void processExtra(Object object, String key, Object value) {
                VO vo = (VO) object;
                vo.getAttributes().put(key, value);
            }

            public Type getExtraType(Object object, String key) {
                if ("value".equals(key)) {
                    return int.class;
                }
                return null;
            }
        };
        ExtraProcessor processor = new MyExtraProcessor();

        VO vo = JSON.parseObject("{\"id\":123,\"value\":\"123456\"}", VO.class, processor);
        System.out.println(Objects.equal(123, vo.getId()));
        System.out.println(Objects.equal(123456, vo.getAttributes().get("value"))); // value本应该是字符串类型的，通过getExtraType的处理变成Integer类
    }
}


