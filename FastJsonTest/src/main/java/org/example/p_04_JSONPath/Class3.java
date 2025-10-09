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
 * 5.3 例3
 * 返回集合中多个元素
 */
public class Class3 {
    public static void main(String[] args) {
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));
        entities.add(new Entity("Yako"));

        List<Entity> result = (List<Entity>) JSONPath.eval(entities, "[1,2]"); // 返回下标为1和2的元素
        Objects.equal(2, result.size());
        Objects.equal(entities.get(1), result.get(0));
        Objects.equal(entities.get(2), result.get(1));

        System.out.println(entities.get(1));
        System.out.println(entities.get(2));
        System.out.println(result.get(0));
        System.out.println(result.get(1));
    }
}
