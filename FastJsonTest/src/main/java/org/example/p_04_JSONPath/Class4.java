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
 * 5.4 例4
 * 按范围返回集合的子集
 */
public class Class4 {
    public static void main(String[] args) {
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));
        entities.add(new Entity("Yako"));

        List<Entity> result = (List<Entity>) JSONPath.eval(entities, "[0:2]"); // 返回下标从0到2的元素
        Objects.equal(3, result.size());
        Objects.equal(entities.get(0), result.get(0));
        Objects.equal(entities.get(1), result.get(1));
        Objects.equal(entities.get(2), result.get(1));
    }
}
