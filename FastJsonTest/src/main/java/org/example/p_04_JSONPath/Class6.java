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
import com.google.common.base.Preconditions;

/**
 * 5.6 例6
 * 根据属性值过滤条件判断是否返回对象，修改对象，数组属性添加元素
 */
public class Class6 {
    public static void main(String[] args) {
        Entity entity = new Entity(1001, "ljw2083");
        Objects.equal(entity , JSONPath.eval(entity, "[id = 1001]"));
        Preconditions.checkArgument(null == JSONPath.eval(entity, "[id = 1002]"));

        JSONPath.set(entity, "id", 123456); //将id字段修改为123456
        Objects.equal(123456, entity.getId().intValue());

        JSONPath.set(entity, "value", new int[0]); //将value字段赋值为长度为0的数组
        JSONPath.arrayAdd(entity, "value", 1, 2, 3); //将value字段的数组添加元素1,2,3

        System.out.println(((int[]) entity.getValue()).length);
    }
}
