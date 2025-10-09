package org.example.package_08_区间;

import com.google.common.collect.BoundType;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-10 17:46
 * @Version v2.0
 */
public class Class_01_区间 {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    public static void test1() {
        // 构建区间
        //  区间实例可以由 Range 类的静态方法获取：
        //
        //(a..b)	open(C, C)
        //[a..b]	closed(C, C)
        //[a..b)	closedOpen(C, C)
        //(a..b]	openClosed(C, C)
        //(a..+∞)	greaterThan(C)
        //[a..+∞)	atLeast(C)
        //(-∞..b)	lessThan(C)
        //(-∞..b]	atMost(C)
        //(-∞..+∞)	all()
        Range.closed("left", "right"); //字典序在"left"和"right"之间的字符串，闭区间
        Range.lessThan(4.0); //严格小于4.0的double值

        /**
         * 此外，也可以明确地指定边界类型来构造区间：
         *
         * 有界区间	range(C, BoundType, C, BoundType)
         * 无上界区间：((a..+∞) 或[a..+∞))	downTo(C, BoundType)
         * 无下界区间：((-∞..b) 或(-∞..b])	upTo(C, BoundType)
         *
         * 这里的 BoundType 是一个枚举类型，包含 CLOSED 和 OPEN 两个值。
         */
        Range.downTo(4, BoundType.CLOSED);// (a..+∞)或[a..+∞)，取决于boundType
        Range.range(1, BoundType.CLOSED, 4, BoundType.OPEN);// [1..4)，等同于Range.closedOpen(1, 4)
    }
    public static void test2() {
        // 区间运算
        /**
         * Range 的基本运算是它的 contains 方法，和你期望的一样，它用来区间判断是否包含某个值。
         * 此外，Range 实例也可以当作 Predicate，并且在函数式编程中使用（译者注：见第 4 章）。任何 Range 实例也都支持 containsAll(Iterable<? extends C>)方法：
         */
        Range.closed(1, 3).contains(2);//return true
        Range.closed(1, 3).contains(4);//return false
        Range.lessThan(5).contains(5); //return false
        Range.closed(1, 4).containsAll(Ints.asList(1, 2, 3)); //return true
    }
    public static void test3() {
        // 查询运算
        /**
         * [hasLowerBound()]和 [hasUpperBound()]：判断区间是否有特定边界，或是无限的；
         * [lowerBoundType()]和 [upperBoundType()]：返回区间边界类型，CLOSED 或 OPEN；如果区间没有对应的边界，抛出 IllegalStateException；
         * [lowerEndpoint()]和 [upperEndpoint()]：返回区间的端点值；如果区间没有对应的边界，抛出 IllegalStateException；
         * [isEmpty()]：判断是否为空区间。
         */
        Range.closedOpen(4, 4).isEmpty(); // returns true
        Range.openClosed(4, 4).isEmpty(); // returns true
        Range.closed(4, 4).isEmpty(); // returns false
        Range.open(4, 4).isEmpty(); // Range.open throws IllegalArgumentException
        Range.closed(3, 10).lowerEndpoint(); // returns 3
        Range.open(3, 10).lowerEndpoint(); // returns 3
        Range.closed(3, 10).lowerBoundType(); // returns CLOSED
        Range.open(3, 10).upperBoundType(); // returns OPEN
    }
    public static void test4() {
        // 关系运算

        /**
         * 包含[enclose]
         * 区间之间的最基本关系就是包含[encloses(Range)]：如果内区间的边界没有超出外区间的边界，则外区间包含内区间。包含判断的结果完全取决于区间端点的比较！
         *
         * [3..6] 包含[4..5] ；
         * (3..6) 包含(3..6) ；
         * [3..6] 包含[4..4)，虽然后者是空区间；
         * (3..6]不 包含[3..6] ；
         * [4..5]不 包含(3..6)，虽然前者包含了后者的所有值，离散域[discrete domains]可以解决这个问题（见 8.5节）；
         * [3..6]不 包含(1..1]，虽然前者包含了后者的所有值。
         * 包含是一种偏序关系[partial ordering]。基于包含关系的概念，Range 还提供了以下运算方法。
         */


        /**
         * 相连[isConnected]
         *
         * Range.isConnected(Range)判断区间是否是相连的。具体来说，isConnected 测试是否有区间同时包含于这两个区间，这等同于数学上的定义”两个区间的并集是连续集合的形式”（空区间的特殊情况除外）。
         *
         * 相连是一种自反的[reflexive]、对称的[symmetric]关系。
         */
        Range.closed(3, 5).isConnected(Range.open(5, 10)); // returns true
        Range.closed(0, 9).isConnected(Range.closed(3, 4)); // returns true
        Range.closed(0, 5).isConnected(Range.closed(3, 9)); // returns true
        Range.open(3, 5).isConnected(Range.open(5, 10)); // returns false
        Range.closed(1, 5).isConnected(Range.closed(6, 10)); // returns false


        /**
         * 交集[intersection]
         * Range.intersection(Range)返回两个区间的交集：既包含于第一个区间，又包含于另一个区间的最大区间。当且仅当两个区间是相连的，它们才有交集。如果两个区间没有交集，该方法将抛出 IllegalArgumentException。
         *
         * 交集是可互换的[commutative] 、关联的[associative] 运算[operation]。
         */
        Range.closed(3, 5).intersection(Range.open(5, 10)); // returns (5, 5]
        Range.closed(0, 9).intersection(Range.closed(3, 4)); // returns [3, 4]
        Range.closed(0, 5).intersection(Range.closed(3, 9)); // returns [3, 5]
        Range.open(3, 5).intersection(Range.open(5, 10)); // throws IAE
        Range.closed(1, 5).intersection(Range.closed(6, 10)); // throws IAE


        /**
         * 跨区间[span]
         * Range.span(Range)返回”同时包括两个区间的最小区间”，如果两个区间相连，那就是它们的并集。
         *
         * span 是可互换的[commutativ] 、关联的[associative] 、闭合的[closed]运算[operation]。
         */
        Range.closed(3, 5).span(Range.open(5, 10)); // returns [3, 10)
        Range.closed(0, 9).span(Range.closed(3, 4)); // returns [0, 9]
        Range.closed(0, 5).span(Range.closed(3, 9)); // returns [0, 9]
        Range.open(3, 5).span(Range.open(5, 10)); // returns (3, 10)
        Range.closed(1, 5).span(Range.closed(6, 10)); // returns [1, 10]
    }
    public static void test5() {
        // 离散域
        System.out.println("=================离散域=================");
        DiscreteDomain<Integer> integers = DiscreteDomain.integers();
        //离散域中两个元素的距离
        System.out.println(integers.distance(0, 10));
        //离散域上界
        System.out.println(integers.maxValue());
        //离散域下界
        System.out.println(integers.minValue());
        //某个元素下一个
        System.out.println(integers.next(0));
        //某个元素上一个
        System.out.println(integers.previous(0));
        ContiguousSet<Integer> integerContiguousSet = ContiguousSet.create(Range.open(1, 9), integers);
        Range<Integer> integerRange = integerContiguousSet.range();
        System.out.println(integerRange);//[2‥8]
        System.out.println(integerContiguousSet.headSet(5).range());//[2‥4]
        System.out.println(integerContiguousSet.tailSet(5).range());//[5‥8]

        //自定义离散域
        EvenIntegerDiscreteDomain evenIntegerDiscreteDomain = new EvenIntegerDiscreteDomain(0, 100);
        System.out.println(String.format("next(5):%s,netx(4):%s,next(3):%s", evenIntegerDiscreteDomain.next(5),
                evenIntegerDiscreteDomain.next(4), evenIntegerDiscreteDomain.next(3)));
        System.out.println(String.format("previous(5):%s,previous(4):%s,previous(3):%s", evenIntegerDiscreteDomain.previous(5),
                evenIntegerDiscreteDomain.previous(4), evenIntegerDiscreteDomain.previous(3)));
        System.out.println(String.format("max:%s,min:%s", evenIntegerDiscreteDomain.maxValue(), evenIntegerDiscreteDomain.minValue()));
        System.out.println(String.format("evenIntegerDiscreteDomain.distance(3, 5)：%s", evenIntegerDiscreteDomain.distance(3, 5)));
        System.out.println(String.format("evenIntegerDiscreteDomain.distance(3, 6)：%s", evenIntegerDiscreteDomain.distance(3, 6)));



    }

    static class EvenIntegerDiscreteDomain extends DiscreteDomain<Integer> {

        private Integer min;
        private Integer max;

        public EvenIntegerDiscreteDomain(int min, int max) {
            min = min % 2 == 0 ? min : min - 1;
            max = max % 2 == 0 ? max : max - 1;
            this.min=min;
            this.max=max;
        }


        @Override
        public Integer next(Integer referValue) {
            int referEvenValue = referValue % 2 == 0 ? referValue : referValue - 1;
            int next = referEvenValue + 2;
            next = next > this.max ? this.max : next;
            return next;
        }

        @Override
        public Integer previous(Integer referValue) {
            int referEvenValue = referValue % 2 == 0 ? referValue : referValue - 1;
            int previous = referEvenValue - 2;
            previous = previous < this.min ? this.min : previous;
            return previous;
        }

        @Override
        public long distance(Integer first, Integer second) {
            first = first % 2 == 0 ? first : first - 1;
            second = second % 2 == 0 ? second : second - 1;
            return (second - first) / 2;
        }

        @Override
        public Integer minValue() {
            return this.min;
        }

        @Override
        public Integer maxValue() {
            return this.max;
        }
    }
}
