package org.example.p_04_JSONPath;

import com.alibaba.fastjson.JSONPath;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

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
public class Class1 {
    public static void main(String[] args) {
        Entity entity = new Entity(123, new Object());

        Objects.equal(entity.getValue(), JSONPath.eval(entity, "$.value"));
        Preconditions.checkArgument(JSONPath.contains(entity, "$.value"));
        Preconditions.checkArgument(JSONPath.containsValue(entity, "$.id", 123));
        Preconditions.checkArgument(JSONPath.containsValue(entity, "$.value", entity.getValue()));
        Objects.equal(2, JSONPath.size(entity, "$"));
        Objects.equal(0, JSONPath.size(new Object[0], "$"));
    }
}
