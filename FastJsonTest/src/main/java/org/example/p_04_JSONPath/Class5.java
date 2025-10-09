package org.example.p_04_JSONPath;

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

import com.alibaba.fastjson.JSONPath;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * 5.5 例5
 * 通过条件过滤，返回集合的子集
 */
public class Class5 {
    public static void main(String[] args) {
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001, "ljw2083"));
        entities.add(new Entity(1002, "wenshao"));
        entities.add(new Entity(1003, "yakolee"));
        entities.add(new Entity(1004, null));

        List<Object> result = (List<Object>) JSONPath.eval(entities, "[id in (1001)]");
        Objects.equal(1, result.size());
        Objects.equal(entities.get(0), result.get(0));
    }
}
