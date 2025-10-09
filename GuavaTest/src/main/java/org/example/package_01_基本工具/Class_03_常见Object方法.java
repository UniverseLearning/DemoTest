package org.example.package_01_基本工具;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-10 17:41
 * @Version v2.0
 */
public class Class_03_常见Object方法 {

    public static void main(String[] args) {

        test1();
        test2();
        test3();
        test4();
    }

    public static void test1() {
        // equals
        //当一个对象中的字段可以为 null 时，实现 Object.equals 方法会很痛苦，因为不得不分别对它们进行 null 检查。使用 Objects.equal 帮助你执行 null 敏感的 equals 判断，从而避免抛出 NullPointerException。例如:
        Objects.equal("a", "a"); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true
    }
    public static void test2() {
        // hashCode
        //用对象的所有字段作散列[hash]运算应当更简单。Guava 的 Objects.hashCode(Object...)会对传入的字段序列计算出合理的、顺序敏感的散列值。你可以使用 Objects.hashCode(field1, field2, …, fieldn)来代替手动计算散列值。

        System.out.println(Objects.hashCode(new Integer(1), "123"));
    }
    public static void test3() {
        // toString
        //好的 toString 方法在调试时是无价之宝，但是编写 toString 方法有时候却很痛苦。使用 Objects.toStringHelper 可以轻松编写有用的 toString 方法。例如：

//        // Returns "ClassName{x=1}"
//        Objects.toStringHelper(this).add("x", 1).toString();
//        // Returns "MyObject{x=1}"
//        Objects.toStringHelper("MyObject").add("x", 1).toString();
    }
    public static void test4() {

        // 实现一个比较器[Comparator]，或者直接实现 Comparable 接口有时也伤不起。考虑一下这种情况：
        class Person implements Comparable<Person> {
            private String lastName;
            private String firstName;
            private int zipCode;

//            public int compareTo(Person other) {
//                int cmp = lastName.compareTo(other.lastName);
//                if (cmp != 0) {
//                    return cmp;
//                }
//                cmp = firstName.compareTo(other.firstName);
//                if (cmp != 0) {
//                    return cmp;
//                }
//                return Integer.compare(zipCode, other.zipCode);
//            }

            public int compareTo(Person other) {
                return ComparisonChain.start()
                        .compare(this.lastName, other.lastName)
                        .compare(this.firstName, other.firstName)
                        .compare(this.zipCode, other.zipCode, Ordering.natural().nullsLast())
                        .result();
            }
        }


    }
}
