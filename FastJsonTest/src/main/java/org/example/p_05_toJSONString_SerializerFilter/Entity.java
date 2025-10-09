package org.example.p_05_toJSONString_SerializerFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-31 9:54
 * @Version v2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entity {
    private Integer id;
    private String name;
}
