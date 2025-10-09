package org.example.p_04_JSONPath;

import com.alibaba.fastjson.JSONPath;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

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

/**
 * 5.2 例2
 * 读取集合多个元素的某个属性
 */
public class Class2 {
    public static void main(String[] args) {
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));

        List<String> names = (List<String>) JSONPath.eval(entities, "$.name"); // 返回enties的所有名称
        Objects.equal(entities.get(0).getName(), names.get(0));
        Objects.equal(entities.get(1).getName(), names.get(1));
    }
}
