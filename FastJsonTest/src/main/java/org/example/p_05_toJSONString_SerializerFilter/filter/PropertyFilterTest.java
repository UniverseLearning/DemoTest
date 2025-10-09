package org.example.p_05_toJSONString_SerializerFilter.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import org.example.p_05_toJSONString_SerializerFilter.Entity;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-31 9:50
 * @Version v2.0
 */
public class PropertyFilterTest {

    /**
     * PropertyFilter 根据 PropertyName和 PropertyValue来判断是否序列化
     */
    public void test() {
        PropertyFilter filter = new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                if ("id".equals(name)) {
                    int id = ((Integer) value).intValue();
                    return id >= 100;
                }
                return false;
            }
        };

        System.out.println(JSON.toJSONString(new Entity(10, "张三"), filter));
        System.out.println(JSON.toJSONString(new Entity(101, "李四"), filter));
    }
}
