package org.example.p_05_toJSONString_SerializerFilter.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.example.p_05_toJSONString_SerializerFilter.Entity;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-31 9:51
 * @Version v2.0
 */
public class ValueFilterTest {

    /**
     * ValueFilter 序列化是修改Value
     */
    public void test() {
        ValueFilter filter = new ValueFilter() {
            @Override
            public Object process(Object object, String name, Object value) {
                if (value instanceof Integer)
                    return ((Integer) value) + 1;
                return value;
            }
        };

        System.out.println(JSON.toJSONString(new Entity(10, "张三"), filter));
        System.out.println(JSON.toJSONString(new Entity(101, "李四"), filter));
    }
}
