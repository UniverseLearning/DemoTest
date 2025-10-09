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
 * 3. 使用format配置日期格式化
 */
public class Class3 {
    public static void main(String[] args) {
        String jsonStr = "{'date': '20240529'}";
        System.out.println(JSON.parseObject(jsonStr, C.class));

        C c = new C(new Date());
        System.out.println(JSON.toJSONString(c));
    }
}
@ToString
@AllArgsConstructor
@NoArgsConstructor
class C {
    // 配置date序列化和反序列使用yyyyMMdd日期格式
    @JSONField(format="yyyyMMdd")
    public Date date;
}