package org.example.p_05_toJSONString_SerializerFilter.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
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
public class AfterFilterTest {

    /**
     * AfterFilter 序列化时在最后添加内容
     * 在序列化对象的所有属性之后执行某些操作,例如调用 writeKeyValue 添加内容
     */
    public void test() {
        SerializeFilter[] filters = new SerializeFilter[2];

        AfterFilter filter1 = new AfterFilter() {
            @Override
            public void writeAfter(Object object) {
                if (object instanceof Entity) {
                    ((Entity) object).setName(((Entity) object).getName() + "======");
                }
            };
        };
        ValueFilter filter2 = new ValueFilter() {
            @Override
            public Object process(Object object, String name, Object value) {
                if (value instanceof String)
                    return ((String) value) + "1";
                return value;
            }
        };
        filters[0] = filter1;
        filters[1] = filter2;



        System.out.println(JSON.toJSONString(new Entity(101, "李四"), filters));
    }
}
