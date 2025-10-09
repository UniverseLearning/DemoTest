package org.example.p_04_JSONPath;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Objects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-29 10:31
 * @Version v2.0
 */
public class Class7 {
    public static void main(String[] args) {
        Map root = Collections.singletonMap("company",
                Collections.singletonMap("departs",
                        Arrays.asList(
                                Collections.singletonMap("id", 1001),
                                Collections.singletonMap("id", 1002),
                                Collections.singletonMap("id", 1003)
                        )
                ));

        List<Object> ids = (List<Object>) JSONPath.eval(root, "$..id");
        Objects.equal(3, ids.size());
        Objects.equal(1001, ids.get(0));
        Objects.equal(1002, ids.get(1));
        Objects.equal(1003, ids.get(2));

        SerializeConfig config;
        SerializerFeature feature;
        SerializeFilter filter;
    }
}
