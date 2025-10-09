package org.example.package_01_基本工具;

import com.google.common.base.Optional;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-10 17:40
 * @Version v2.0
 */
public class Class_01_使用和避免null {
    public static void main(String[] args) {

        /**
         * 创建 Optional 实例（以下都是静态方法）：
         *
         *      Optional.of(T)	            创建指定引用的 Optional 实例，若引用为 null 则快速失败
         *      Optional.absent()	        创建引用缺失的 Optional 实例
         *      Optional.fromNullable(T)	创建指定引用的 Optional 实例，若引用为 null 则表示缺失
         *
         * 用 Optional 实例查询引用（以下都是非静态方法）：
         *
         *      boolean isPresent()	        如果 Optional 包含非 null 的引用（引用存在），返回true
         *      T get()	                    返回 Optional 所包含的引用，若引用缺失，则抛出 java.lang.IllegalStateException
         *      T or(T)	                    返回 Optional 所包含的引用，若引用缺失，返回指定的值
         *      T orNull()	                返回 Optional 所包含的引用，若引用缺失，返回 null
         *      Set<T> asSet()	            返回 Optional 所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。
         */
        Optional<Integer> possible = Optional.of(5);
        System.out.println(possible.isPresent()); // returns true
        System.out.println(possible.get()); // returns 5



    }
}
