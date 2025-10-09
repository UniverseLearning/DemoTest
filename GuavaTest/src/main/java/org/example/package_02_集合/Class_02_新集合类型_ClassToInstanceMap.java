package org.example.package_02_集合;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-11 10:52
 * @Version v2.0
 */

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

/**
 * 定义：
 *
 * ClassToInstanceMap 是一种特殊的 Map：它的键是类型，而值是符合键所指类型的对象。
 *
 * 为了扩展 Map 接口，ClassToInstanceMap 额外声明了两个方法：T getInstance(Class) 和 T putInstance(Class, T)，从而避免强制类型转换，同时保证了类型安全。
 *
 * 对于 ClassToInstanceMap，Guava 提供了两种有用的实现：MutableClassToInstanceMap 和 ImmutableClassToInstanceMap。
 */
public class Class_02_新集合类型_ClassToInstanceMap {

    public static void main(String[] args) {
        ClassToInstanceMap<Number> numberDefaults= MutableClassToInstanceMap.create();
        numberDefaults.putInstance(Integer.class, Integer.valueOf(0));
        numberDefaults.putInstance(Long.class, Long.valueOf(1));
        System.out.println(numberDefaults);
        /**
         * 从技术上讲，从技术上讲，ClassToInstanceMap<B&gt 实现了 Map<Class<? extends B>, B>——或者换句话说，是一个映射 B 的子类型到对应实例的 Map。
         * 这让 ClassToInstanceMap 包含的泛型声明有点令人困惑，但请记住 B 始终是 Map 所支持类型的上界——通常 B 就是 Object。
         */
    }
}
