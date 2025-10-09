package org.example.package_02_集合;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-11 11:09
 * @Version v2.0
 */

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

/**
 * RangeMap 描述了”不相交的、非空的区间”到特定值的映射。和 RangeSet 不同，RangeMap 不会合并相邻的映射，即便相邻的区间映射到相同的值。例如：
 */
public class Class_02_新集合类型_RangeMap {
    public static void main(String[] args) {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); //{[1,10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo", (10,20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); //{[1,3] => "foo", (3,5) => "bar", (11,20) => "foo"}

        /**
         * RangeMap 提供两个视图：
         *
         * asMapOfRanges()：         用 Map<Range, V>表现 RangeMap。这可以用来遍历 RangeMap。
         * subRangeMap(Range)：      用 RangeMap 类型返回 RangeMap 与给定 Range 的交集视图。这扩展了传统的 headMap、subMap 和 tailMap 操作。
         */
    }
}
