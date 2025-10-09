package org.example.package_01_基本工具;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import javax.annotation.Nullable;
import java.util.Comparator;

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
public class Class_04_排序 {
    public static void main(String[] args) {
        // =============创建排序器======================
        // 方法一 natural()	对可排序类型做自然排序，如数字按大小，日期按先后排序
        Ordering.natural();
        // 方法二 usingToString()	按对象的字符串形式做字典排序[lexicographical ordering]
        Ordering.usingToString();
        // 方法三 from(Comparator)	把给定的 Comparator 转化为排序器
        Ordering.from((Comparator<Integer>) (o1, o2) -> 0);
        // 方法四 现自定义的排序器时，除了用上面的 from 方法，也可以跳过实现 Comparator，而直接继承 Ordering
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };

        // =============== 衍生其它排序器 ==============================
        /**
         * 链式调用方法：通过链式调用，可以由给定的排序器衍生出其它排序器
         *
         *      方法	                        描述
         *      reverse()	                获取语义相反的排序器
         *      nullsFirst()	            使用当前排序器，但额外把 null 值排到最前面。
         *      nullsLast()	                使用当前排序器，但额外把 null 值排到最后面。
         *      compound(Comparator)	    合成另一个比较器，以处理当前排序器中的相等情况。
         *      lexicographical()	        基于处理类型 T 的排序器，返回该类型的可迭代对象 Iterable<T>的排序器。
         *      onResultOf(Function)	    对集合中元素调用 Function，再按返回值用当前排序器排序。
         */

        // 考虑到排序器应该能处理 sortedBy 为 null 的情况，我们可以使用下面的链式调用来合成排序器：
        class Foo {
            @Nullable
            String sortedBy;
            int notSortedBy;
        }
        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            public String apply(Foo foo) {
                return foo.sortedBy;
            }
        });

        // ========================运用排序器=======================================
        /**
         * 运用排序器：Guava 的排序器实现有若干操纵集合或元素值的方法
         *
         *      方法	                                        描述	                                                                        另请参见
         *      greatestOf(Iterable iterable, int k)	    获取可迭代对象中最大的k个元素。	                                                leastOf
         *      isOrdered(Iterable)	                        判断可迭代对象是否已按排序器排序：允许有排序值相等的元素。	                            isStrictlyOrdered
         *      sortedCopy(Iterable)	                    判断可迭代对象是否已严格按排序器排序：不允许排序值相等的元素。	                        immutableSortedCopy
         *      min(E, E)	                                返回两个参数中最小的那个。如果相等，则返回第一个参数。	                            max(E, E)
         *      min(E, E, E, E...)	                        返回多个参数中最小的那个。如果有超过一个参数都最小，则返回第一个最小的参数。	            max(E, E, E, E...)
         *      min(Iterable)	                            返回迭代器中最小的元素。如果可迭代对象中没有元素，则抛出 NoSuchElementException。	    max(Iterable), min(Iterator), max(Iterator)
         */

        System.out.println(Ordering.natural().greatestOf(Lists.newArrayList(1, 2, 3, 444, 5, 66, 999966666, 1), 2));
        System.out.println(Ordering.natural().isOrdered(Lists.newArrayList(1, 2, 3, 444, 5, 66, 999966666, 1)));
        System.out.println(Ordering.natural().sortedCopy(Lists.newArrayList(1, 2, 3, 444, 1)));
    }
}
