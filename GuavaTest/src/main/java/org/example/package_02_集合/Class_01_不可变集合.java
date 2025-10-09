package org.example.package_02_集合;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-10 17:42
 * @Version v2.0
 */

/**
 * 为什么要使用不可变集合
 * 不可变对象有很多优点，包括：
 *
 *      1.当对象被不可信的库调用时，不可变形式是安全的；
 *      2.不可变对象被多个线程调用时，不存在竞态条件问题
 *      3.不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
 *      4.不可变对象因为有固定不变，可以作为常量来安全使用。
 */

// =====================================================================================================================

/**
 *      可变集合接口	            属于JDK还是Guava	                不可变版本
 *      Collection	            JDK	                            ImmutableCollection
 *      List	                JDK	                            ImmutableList
 *      Set	                    JDK	                            ImmutableSet
 *      SortedSet/NavigableSet	JDK	                            ImmutableSortedSet
 *      Map	                    JDK	                            ImmutableMap
 *      SortedMap	            JDK	                            ImmutableSortedMap
 *      Multiset	            Guava	                        ImmutableMultiset
 *      SortedMultiset	        Guava	                        ImmutableSortedMultiset
 *      Multimap	            Guava	                        ImmutableMultimap
 *      ListMultimap	        Guava	                        ImmutableListMultimap
 *      SetMultimap	            Guava	                        ImmutableSetMultimap
 *      BiMap	                Guava	                        ImmutableBiMap
 *      ClassToInstanceMap	    Guava	                        ImmutableClassToInstanceMap
 *      Table	                Guava	                        ImmutableTable
 */
public class Class_01_不可变集合 {
    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "purple");

    public static void main(String[] args) {
        createImmutable();
        sortImmutable();
        asListImmutable();
    }

    /**
     * 不可变集合测创建
     */
    public static void createImmutable() {
        // 方式一：copyOf 方法，如 ImmutableSet.copyOf(set);
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        ImmutableSet<Integer> immutableSet = ImmutableSet.copyOf(set);

        // 方式二：of 方法，如 ImmutableSet.of(“a”, “b”, “c”)或 ImmutableMap.of(“a”, 1, “b”, 2);
        final ImmutableSet<String> immutableSet1 = ImmutableSet.of("a", "b", "c");
        final ImmutableMap<String, Integer> immutableMap = ImmutableMap.of("a", 1, "b", 2);

        // 方式三：Builder 工具，如
        final ImmutableSet<Color> immutableSet2 = ImmutableSet.<Color>builder()
                .add(new Color(0, 191, 255))
                .build();
    }

    /**
     * 此外，对有序不可变集合来说，排序是在构造集合的时候完成的，如
     */
    public static void sortImmutable() {
        ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
    }

    /**
     * 所有不可变集合都有一个 asList()方法提供 ImmutableList 视图，来帮助你用列表形式方便地读取集合元素。例如，你可以使用 sortedSet.asList().get(k)从 ImmutableSortedSet 中读取第 k 个最小元素。
     *
     * asList()返回的 ImmutableList 通常是——并不总是——开销稳定的视图实现，而不是简单地把元素拷贝进 List。也就是说，asList 返回的列表视图通常比一般的列表平均性能更好，比如，在底层集合支持的情况下，它总是使用高效的 contains 方法。
     */
    public static void asListImmutable() {
        ImmutableSet.of("a", "b", "c").asList();
    }
}
