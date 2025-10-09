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
import lombok.Data;

/**
 * 7、JSONField alternateNames
 * 在fastjson在1.2.21版本中提供了一个借鉴自gson的特性，支持反序列化时使用多个不同的字段名称，使用的方式是配置JSONField的alternateNames。
 */
public class Class7 {
    public static void main(String[] args) {
        String jsonVal0 = "{\"id\":5001,\"name\":\"Jobs\"}";
        String jsonVal1 = "{\"id\":5382,\"user\":\"Mary\"}";
        String jsonVal2 = "{\"id\":2341,\"person\":\"Bob\"}";

        G obj0 = JSON.parseObject(jsonVal0, G.class);

        G obj1 = JSON.parseObject(jsonVal1, G.class);

        G obj2 = JSON.parseObject(jsonVal2, G.class);

        System.out.println(obj0);
        System.out.println(obj1);
        System.out.println(obj2);
    }
}
@Data
class G {
    public int id;

    @JSONField(alternateNames = {"user", "person"})
    public String name;
}