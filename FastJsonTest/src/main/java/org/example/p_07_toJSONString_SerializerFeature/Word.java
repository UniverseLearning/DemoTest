package org.example.p_07_toJSONString_SerializerFeature;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
public class Word {
    private String d;
    private String e;
    private String f;
    private String a;
    private int b;
    private boolean c;
    private Integer h;
    private Date date;
    private Map<String , Object> map;
    private List<User> list;
}
