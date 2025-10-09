package org.example.p_05_toJSONString_SerializerFilter.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PascalNameFilter;
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
public class NameFilterTest {

    /**
     * NameFilter 序列化时修改Key
     * 如果需要修改Key,process返回值则可
     */
    public void test() {
        NameFilter filter = new NameFilter() {
            @Override
            public String process(Object object, String name, Object value) {
                if (name.equals("id"))
                    return name.toLowerCase();
                else
                    return name.toUpperCase();
            }
        };

        System.out.println(JSON.toJSONString(new Entity(10, "张三"), filter));
        System.out.println(JSON.toJSONString(new Entity(101, "李四"), filter));
    }

    /**
     * fastjson内置一个PascalNameFilter，用于输出将首字符大写的Pascal风格。 例如：
     */
    public void test1() {
        PascalNameFilter filter = new PascalNameFilter();

        System.out.println(JSON.toJSONString(new Entity(10, "张三"), filter));
        System.out.println(JSON.toJSONString(new Entity(101, "李四"), filter));
    }
}
