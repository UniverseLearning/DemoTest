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
 * 8、JSONField jsonDirect
 * 在fastjson-1.2.12版本中，JSONField支持一个新的配置项jsonDirect，它的用途是：当你有一个字段是字符串类型，里面是json格式数据，你希望直接输入，而不是经过转义之后再输出。
 */
public class Class8 {
    public static void main(String[] args) {
        H model = new H();
        model.id = 1001;
        model.value = "{'abc': 123}";
        String json = JSON.toJSONString(model);
        System.out.println(json);

        I model1 = new I();
        model1.id = 1001;
        model1.value = "{'abc': 123,123123123sdfsdf';'ew;r';wer}";
        String json1 = JSON.toJSONString(model1);
        System.out.println(json1);
    }
}
@Data
class H {
    public int id;
    @JSONField(jsonDirect=true)
    public String value;
}
@Data
class I {
    public int id;
    public String value;
}