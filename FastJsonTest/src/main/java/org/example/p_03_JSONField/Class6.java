package org.example.p_03_JSONField;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-29 9:18
 * @Version v2.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 6. 使用serializeUsing制定属性的序列化类
 * 在fastjson 1.2.16版本之后，JSONField支持新的定制化配置serializeUsing，可以单独对某一个类的某个属性定制序列化，比如：
 */
public class Class6 {
    public static void main(String[] args) {
        F model = new F();
        model.value = 100;
        String json = JSON.toJSONString(model);
        System.out.println(json);
    }
}
class F {
    @JSONField(serializeUsing = ModelValueSerializer.class)
    public int value;
}