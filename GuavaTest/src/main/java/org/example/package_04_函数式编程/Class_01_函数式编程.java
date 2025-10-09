package org.example.package_04_函数式编程;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.math.IntMath;

import javax.annotation.Nullable;
import java.math.RoundingMode;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-10 17:44
 * @Version v2.0
 */
public class Class_01_函数式编程 {
    public static void main(String[] args) {
        /**
         * 操作 Functions 和 Predicates
         * Functions 提供简便的 Function 构造和操作方法，包括：
         *      forMap(Map<A, B>)
         *      compose(Function<B, C>, Function<A, B>)
         *      constant(T)
         *      identity()
         *      toStringFunction()
         *
         * 相应地，Predicates 提供了更多构造和处理 Predicate 的方法，下面是一些例子：
         *      instanceOf(Class)
         *      assignableFrom(Class)
         *      contains(Pattern)
         *      in(Collection)
         *      isNull()
         *      alwaysFalse()
         *      alwaysTrue()
         *      equalTo(Object)
         *      compose(Predicate, Function)
         *      and(Predicate...)
         *      or(Predicate...)
         *      not(Predicate)
         */

        Function<Object, Object> forMap = Functions.forMap(Maps.newHashMap());
        Function<String, Integer> compose = Functions.compose(new Function<Integer, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable Integer input) {
                return (2) + input;
            }
        }, new Function<String, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable String input) {
                return Integer.parseInt(input);
            }
        });
        Lists.newArrayList("1", "2", "3").stream().map(compose).forEach(System.out::println);


    }

    public static void test1() {
        // 使用一： 断言
        /**
         * 断言的最基本应用就是过滤集合。所有 Guava 过滤方法都返回”视图”——译者注：即并非用一个新的集合表示过滤，而只是基于原集合的视图。
         *
         *      集合类型	        过滤方法
         *      Iterable	    Iterables.filter(Iterable, Predicate)
         *                      FluentIterable.filter(Predicate)
         *      Iterator	    Iterators.filter(Iterator, Predicate)
         *      Collection	    Collections2.filter(Collection, Predicate)
         *      Set	            Sets.filter(Set, Predicate)
         *      SortedSet	    Sets.filter(SortedSet, Predicate)
         *      Map	            Maps.filterKeys(Map, Predicate)
         *                      Maps.filterValues(Map, Predicate)Maps.filterEntries(Map, Predicate)
         *      SortedMap	    Maps.filterKeys(SortedMap, Predicate)
         *                      Maps.filterValues(SortedMap, Predicate)
         *                      Maps.filterEntries(SortedMap, Predicate)
         *      Multimap	    Multimaps.filterKeys(Multimap, Predicate)
         *                      Multimaps.filterValues(Multimap, Predicate)
         *                      Multimaps.filterEntries(Multimap, Predicate)
         */

    }
    public static void test2() {
        // 使用二： Iterables
        /**
         *  除了简单过滤，Guava 另外提供了若干用 Predicate 处理 Iterable 的工具——通常在 Iterables 工具类中，或者是 FluentIterable 的”fluent”（链式调用）方法。
         *
         *  Iterables方法签名	                            说明	                                                                另请参见
         *  boolean all(Iterable, Predicate)	            是否所有元素满足断言？懒实现：如果发现有元素不满足，不会继续迭代	            Iterators.all(Iterator, Predicate)
         *                                                                                                                      FluentIterable.allMatch(Predicate)
         *  boolean any(Iterable, Predicate)	            是否有任意元素满足元素满足断言？懒实现：只会迭代到发现满足的元素	            Iterators.any(Iterator, Predicate)
         *                                                                                                                      FluentIterable.anyMatch(Predicate)
         *  T find(Iterable, Predicate)	                    循环并返回一个满足元素满足断言的元素，如果没有则抛出 NoSuchElementException	Iterators.find(Iterator, Predicate)
         *                                                                                                                      Iterables.find(Iterable, Predicate, T default)
         *                                                                                                                      Iterators.find(Iterator, Predicate, T default)
         *  Optional<T> tryFind(Iterable, Predicate)	    返回一个满足元素满足断言的元素，若没有则返回Optional.absent()	            Iterators.find(Iterator, Predicate)
         *                                                                                                                      Iterables.find(Iterable, Predicate, T default)
         *                                                                                                                      Iterators.find(Iterator, Predicate, T default)
         *  indexOf(Iterable, Predicate)	                返回第一个满足元素满足断言的元素索引值，若没有返回-1	                        Iterators.indexOf(Iterator, Predicate)
         *  removeIf(Iterable, Predicate)	                移除所有满足元素满足断言的元素，实际调用Iterator.remove()方法	            Iterators.removeIf(Iterator, Predicate)
         */
    }
    public static void test3() {
        // 使用三： 函数
        /**
         *      集合类型	            转换方法
         *      Iterable	        Iterables.transform(Iterable, Function)
         *                          FluentIterable.transform(Function)
         *      Iterator	        Iterators.transform(Iterator, Function)
         *      Collection	        Collections2.transform(Collection, Function)
         *      List	            Lists.transform(List, Function)
         *      Map*	            Maps.transformValues(Map, Function)
         *                          Maps.transformEntries(Map, EntryTransformer)
         *      SortedMap*	        Maps.transformValues(SortedMap, Function)
         *                          Maps.transformEntries(SortedMap, EntryTransformer)
         *      Multimap*	        Multimaps.transformValues(Multimap, Function)
         *                          Multimaps.transformEntries(Multimap, EntryTransformer)
         *      ListMultimap*	    Multimaps.transformValues(ListMultimap, Function)
         *                          Multimaps.transformEntries(ListMultimap, EntryTransformer)
         *      Table	            Tables.transformValues(Table, Function)
         */
    }
    public static void test4() {
        // 使用四： 其它
        /**
         *      Ordering	Ordering.onResultOf(Function)
         *      Predicate	Predicates.compose(Predicate, Function)
         *      Equivalence	Equivalence.onResultOf(Function)
         *      Supplier	Suppliers.compose(Function, Supplier)
         *      Function	Functions.compose(Function, Function)
         */
    }
}
