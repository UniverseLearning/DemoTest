package org.example.p_08_序列化_反序列化;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-31 15:32
 * @Version v2.0
 */
@Data
class Student {

    private String id;

    @JSONField(serializeUsing = CustomSerializer.class, deserializeUsing = CustomDeserializer.class)
    private String name;
    private Integer age;
}

public class Test3 {

    public static void main(String[] args) {
        Student student = new Student();
        student.setId("2");
        student.setName("瓜田李下");
        student.setAge(20);

        String jsonString = JSON.toJSONString(student);
        System.out.println(jsonString);

        Student s = JSON.parseObject(jsonString, Student.class);
        System.out.println(s);
    }
}