package org.example.p_03_JSONField;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-29 9:17
 * @Version v2.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 2. JSONField配置方式
 * FieldInfo可以配置在getter/setter方法或者字段上。例如：
 */
public class Class2 {
    public static void main(String[] args) {
        A a = new A(1);
        System.out.println(JSON.toJSONString(a));
        System.out.println(JSON.parseObject(JSON.toJSONString(a), A.class));

        B b = new B(2);
        System.out.println(JSON.toJSONString(b));
        System.out.println(JSON.parseObject(JSON.toJSONString(b), B.class));
    }
}
@NoArgsConstructor
@AllArgsConstructor
class A {
    private int id;

    @JSONField(name="ID")
    public int getId() {return id;}
    @JSONField(name="ID")
    public void setId(int value) {this.id = id;}
}
@NoArgsConstructor
@AllArgsConstructor
class B {
    @JSONField(name="ID")
    private int id;

    public int getId() {return id;}
    public void setId(int value) {this.id = id;}
}