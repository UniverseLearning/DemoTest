package org.example.package_02_集合;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-11 11:00
 * @Version v2.0
 */

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

/**
 * RangeSet描述了一组不相连的、非空的区间。当把一个区间添加到可变的RangeSet时，所有相连的区间会被合并，空区间会被忽略。例如：
 */
public class Class_02_新集合类型_RangeSet {
    public static void main(String[] args) {

        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1,10]}
        rangeSet.add(Range.closedOpen(11, 15));//不相连区间:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(15, 20)); //相连区间; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0, 0)); //空区间; {[1,10], [11,20)}
        rangeSet.remove(Range.open(5, 10)); //分割[1, 10]; {[1,5], [10,10], [11,20)}

        System.out.println(rangeSet);

        /**
         * RangeSet 的实现支持非常广泛的视图：
         *
         * complement()：            返回 RangeSet 的补集视图。complement 也是 RangeSet 类型,包含了不相连的、非空的区间。
         * subRangeSet(Range)：      返回 RangeSet 与给定 Range 的交集视图。这扩展了传统排序集合中的 headSet、subSet 和 tailSet 操作。
         * asRanges()：              用 Set<Range>表现 RangeSet，这样可以遍历其中的 Range。
         * asSet(DiscreteDomain)    （仅 ImmutableRangeSet 支持）：用 ImmutableSortedSet表现 RangeSet，以区间中所有元素的形式而不是区间本身的形式查看。（这个操作不支持 DiscreteDomain 和 RangeSet 都没有上边界，或都没有下边界的情况）
         */
        System.out.println(rangeSet.complement());
        System.out.println(rangeSet.subRangeSet(Range.closedOpen(15, 20)));
        System.out.println(rangeSet.asRanges().toArray()[0]);

        /**
         * RangeSet 的查询方法
         * 为了方便操作，RangeSet 直接提供了若干查询方法，其中最突出的有:
         *
         * contains(C)：             RangeSet 最基本的操作，判断 RangeSet 中是否有任何区间包含给定元素。
         * rangeContaining(C)：      返回包含给定元素的区间；若没有这样的区间，则返回 null。
         * encloses(Range)：         简单明了，判断 RangeSet 中是否有任何区间包括给定区间。
         * span()：                  返回包括 RangeSet 中所有区间的最小区间。
         */
        System.out.println(rangeSet.contains(10));
        System.out.println(rangeSet.rangeContaining(15));
        System.out.println(rangeSet.encloses(Range.closedOpen(15, 25)));
        System.out.println(rangeSet.span());

    }
}
