package org.example.p_07_toJSONString_SerializerFeature;

import lombok.Data;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-31 14:02
 * @Version v2.0
 */
@Data
public class User {
    private int id;
    private String name;
    private String add;
    private String old;
}
