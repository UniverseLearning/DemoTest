package org.example.p_05_toJSONString_SerializerFilter.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
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
public class PropertyPreFilterTest {

    /**
     * PropertyPreFilter 根据PropertyName判断是否序列化
     * 和PropertyFilter不同只根据object和name进行判断，在调用getter之前，这样避免了getter调用可能存在的异常。
     *
     * 和 PropertyFilter 区别： 只有 Object、Name，没有 value
     */
    public void test() {
        PropertyPreFilter filter = new PropertyPreFilter() {
            @Override
            public boolean apply(JSONSerializer jsonSerializer, Object object, String name) {
                if ("id".equals(name)) {
                    return true;
                }
                return false;
            }
        };

        System.out.println(JSON.toJSONString(new Entity(10, "张三"), filter));
        System.out.println(JSON.toJSONString(new Entity(101, "李四"), filter));
    }
}
