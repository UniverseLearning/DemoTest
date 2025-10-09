package org.example.p_01_快速上手;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-28 13:40
 * @Version v2.0
 */
public class Main {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test7();
    }

    public static void test1() {
        // 将 Java 对象转换为 JSON 格式
        List<Person> listOfPersons = new ArrayList<Person>();
        listOfPersons.add(new Person(15, "John Doe", new Date()));
        listOfPersons.add(new Person(20, "Janette Doe", new Date()));

        System.out.println(JSON.toJSONString(listOfPersons));
    }

    public static void test2() {
        // 我们还可以自定义输出，并控制字段的排序，日期显示格式，序列化标记等。
        /**
         * 以上代码中我们列出了基本参数类别，并使用 @JSONField 注解，以便实现自定义转换：
         *
         *      1.format 参数用于格式化 date 属性。
         *      2.默认情况下， FastJson 库可以序列化 Java bean 实体， 但我们可以使用 serialize 指定字段不序列化。
         *      3.使用 ordinal 参数指定字段的顺序
         */
        List<Person1> listOfPersons = new ArrayList<Person1>();
        listOfPersons.add(new Person1(15, "John Doe", "张三", new Date()));
        listOfPersons.add(new Person1(20, "Janette Doe", "李四", new Date()));

        System.out.println(JSON.toJSONString(listOfPersons));

    }

    public static void test3() {
        // FastJson 还支持 BeanToArray 序列化功能：
        List<Person1> listOfPersons = new ArrayList<Person1>();
        listOfPersons.add(new Person1(15, "John Doe", "张三", new Date()));
        listOfPersons.add(new Person1(20, "Janette Doe", "李四", new Date()));

        System.out.println(JSON.toJSONString(listOfPersons, SerializerFeature.BeanToArray));
    }

    public static void test4() {
        // 创建 JSON 对象
        /**
         * 创建 JSON 对象非常简单，只需使用 JSONObject（fastJson提供的json对象） 和 JSONArray（fastJson提供json数组对象） 对象即可。
         * 我们可以把JSONObject 当成一个 Map<String,Object> 来看，只是 JSONObject 提供了更为丰富便捷的方法，方便我们对于对象属性的操作。
         *
         * 同样我们可以把 JSONArray 当做一个 List<Object>，可以把 JSONArray 看成 JSONObject 对象的一个集合。
         *
         */
        /**
         * 此外，由于 JSONObject 和 JSONArray 继承了 JSON，所以说也可以直接使用两者对 JSON 格式字符串与 JSON 对象及 javaBean 之间做转换，不过为了避免混淆我们还是使用 JSON。
         */

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("AGE", 10);
            jsonObject.put("FULL NAME", "Doe " + i);
            jsonObject.put("DATE OF BIRTH", "2016/12/12 12:12:12");
            jsonArray.add(jsonObject);
        }
        System.out.println(JSON.toJSONString(jsonArray));
    }

    public static void test5() {
        // JSON 字符串转换为 Java 对象
        Person2 person = new Person2(20, "John", "Doe", new Date());
        String jsonObject = JSON.toJSONString(person);
        Person2 newPerson = JSON.parseObject(jsonObject, Person2.class);

        System.out.println(newPerson.getAge());
        System.out.println(newPerson.getFirstName());
    }

    public static void test6() {
        /**
         * 使用 ContextValueFilter 配置 JSON 转换
         * 在某些场景下，对Value做过滤，需要获得所属JavaBean的信息，包括类型、字段、方法等。在fastjson-1.2.9中，提供了ContextValueFilter，类似于之前版本提供的ValueFilter，只是多了BeanContext参数可用。
         */
        List<Person1> listOfPersons = new ArrayList<Person1>();
        listOfPersons.add(new Person1(15, "John Doe", "张三", new Date()));
        listOfPersons.add(new Person1(20, "Janette Doe", "李四", new Date()));

        ContextValueFilter valueFilter = new ContextValueFilter () {
            @Override
            public Object process(BeanContext context, Object object, String name, Object value) {
                if (name.equals("DATE OF BIRTH")) {
                    return "NOT TO DISCLOSE";
                }
                if (value.equals("John Doe")) {
                    return ((String) value).toUpperCase();
                } else {
                    return null;
                }
            }
        };
        String jsonOutput = JSON.toJSONString(listOfPersons, valueFilter);
        System.out.println(jsonOutput);
    }

    public static void test7() {
        /**
         * 使用 NameFilter 和 SerializeConfig
         * NameFilter: 序列化时修改 Key。
         *
         * SerializeConfig：内部是个map容器主要功能是配置并记录每种Java类型对应的序列化类。
         *
         */

        /**
         * 实例中我们声明了 formatName 过滤器使用 NameFilter 匿名类来处理字段名称。 新创建的过滤器与 Person 类相关联，然后添加到全局实例，它是 SerializeConfig 类中的静态属性。
         *
         * 现在我们可以轻松地将对象转换为JSON格式。
         *
         * 注意我们使用的是 toJSONStringWithDateFormat() 而不是 toJSONString() ，它可以更快速的格式化日期。
         */
        List<Person1> listOfPersons = new ArrayList<Person1>();
        listOfPersons.add(new Person1(15, "John Doe", "张三", new Date()));
        listOfPersons.add(new Person1(20, "Janette Doe", "李四", new Date()));

        NameFilter formatName = new NameFilter() {
            public String process(Object object, String name, Object value) {
                return name.toLowerCase().replace(" ", "_");
            }
        };
        SerializeConfig.getGlobalInstance().addFilter(Person1.class,  formatName);
        String jsonOutput =
                JSON.toJSONStringWithDateFormat(listOfPersons, "yyyy-MM-dd");
        System.out.println(jsonOutput);
    }
}

@Data
class Person {

    @JSONField(name = "AGE")
    private int age;

    @JSONField(name = "FULL NAME")
    private String fullName;

    @JSONField(name = "DATE OF BIRTH")
    private Date dateOfBirth;

    public Person(int age, String fullName, Date dateOfBirth) {
        super();
        this.age = age;
        this.fullName= fullName;
        this.dateOfBirth = dateOfBirth;
    }

    // 标准 getters & setters
}

@Data
class Person1 {
    @JSONField(name="AGE", serialize=false)
    private int age;

    @JSONField(name="LAST NAME", ordinal = 2)
    private String lastName;

    @JSONField(name="FIRST NAME", ordinal = 1)
    private String firstName;

    @JSONField(name="DATE OF BIRTH", format="dd/MM/yyyy", ordinal = 3)
    private Date dateOfBirth;

    public Person1(int age, String lastName, String firstName, Date dateOfBirth) {
        super();
        this.age = age;
        this.lastName= lastName;
        this.firstName= firstName;
        this.dateOfBirth = dateOfBirth;
    }
}

@Setter
@Getter
class Person2 {
    @JSONField(name="AGE", serialize=false, deserialize = false)
    private int age;

    @JSONField(name="LAST NAME", ordinal = 2)
    private String lastName;

    @JSONField(name="FIRST NAME", ordinal = 1)
    private String firstName;

    @JSONField(name="DATE OF BIRTH", ordinal = 3)
    private Date dateOfBirth;

    public Person2(int age, String lastName, String firstName, Date dateOfBirth) {
        super();
        this.age = age;
        this.lastName= lastName;
        this.firstName= firstName;
        this.dateOfBirth = dateOfBirth;
    }
}