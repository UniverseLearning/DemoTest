package org.example.p_08_序列化_反序列化;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-31 15:31
 * @Version v2.0
 */
public class CustomDeserializer implements ObjectDeserializer {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        if (type.getTypeName().contains("String")){
            String text=(String)parser.parse(fieldName);

            return (T)("hello "+text.split(" ")[1]);
        }

        return (T)parser.parse(fieldName);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
