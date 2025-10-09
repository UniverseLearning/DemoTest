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
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 4. 使用serialize/deserialize指定字段不序列化
 */
public class Class4 {
    public static void main(String[] args) {
        String jsonStr = "{'id': 1, 'name': 'Adam', 'date': '20240509', 'birth': '20240530'}";
        System.out.println(JSON.parseObject(jsonStr, D.class));

        D d = new D(2, "Robert", new Date(), new Date());
        System.out.println(JSON.toJSONString(d));
    }
}
@NoArgsConstructor
@AllArgsConstructor
@ToString
class D {
    public Integer id;
    @JSONField(serialize = false)
    public String name;

    @JSONField(deserialize = false)
    public Date date;

    public Date birth;
}