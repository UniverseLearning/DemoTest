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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 5. 使用ordinal指定字段的顺序
 * 缺省fastjson序列化一个java bean，是根据fieldName的字母序进行序列化的，你可以通过ordinal指定字段的顺序。这个特性需要1.1.42以上版本。
 */
public class Class5 {
    public static void main(String[] args) {
        E e = new E(0,1,2);
        System.out.println(JSON.toJSONString(e));
    }
}
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
class E {
    @JSONField(ordinal = 3)
    private int f0;

    @JSONField(ordinal = 2)
    private int f1;

    @JSONField(ordinal = 1)
    private int f2;
}