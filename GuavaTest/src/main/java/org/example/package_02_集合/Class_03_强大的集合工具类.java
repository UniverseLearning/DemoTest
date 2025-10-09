package org.example.package_02_集合;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-10 17:43
 * @Version v2.0
 */

import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import com.sun.corba.se.spi.protocol.RetryType;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * 我们用相对直观的方式把工具类与特定集合接口的对应关系归纳如下：
 *
 *      集合接口	        属于JDK还是Guava	        对应的Guava工具类
 *      Collection	    JDK	                    Collections2：不要和 java.util.Collections 混淆
 *      List	        JDK	                    Lists
 *      Set	            JDK	                    Sets
 *      SortedSet	    JDK	                    Sets
 *      Map	            JDK	                    Maps
 *      SortedMap	    JDK	                    Maps
 *      Queue	        JDK	                    Queues
 *      Multiset	    Guava	                Multisets
 *      Multimap	    Guava	                Multimaps
 *      BiMap	        Guava	                Maps
 *      Table	        Guava	                Tables
 */
public class Class_03_强大的集合工具类 {

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
        // Guava 提供了能够推断范型的静态工厂方法：
        ArrayList<String> stringArrayList = Lists.newArrayList();
        final HashSet<Object> objects = Sets.newHashSet();
        final HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap();
        final ConcurrentMap<Object, Object> objectObjectConcurrentMap = Maps.newConcurrentMap();

        // 用工厂方法模式，我们可以方便地在初始化时就指定起始元素。
        Set<Integer> copySet = Sets.newHashSet(Lists.newArrayList(1,2,3));

        // 此外，通过为工厂方法命名（Effective Java 第一条），我们可以提高集合初始化大小的可读性：
        List<Integer> exactly10 = Lists.newArrayListWithCapacity(10);
        List<Integer> approx100 = Lists.newArrayListWithExpectedSize(100);
        Set<Integer> approx100Set = Sets.newHashSetWithExpectedSize(100);
    }

    public static void test2() {
        /**
         * 在可能的情况下，Guava 提供的工具方法更偏向于接受 Iterable 而不是 Collection 类型。在 Google，对于不存放在主存的集合——比如从数据库或其他数据中心收集的结果集，因为实际上还没有攫取全部数据，这类结果集都不能支持类似 size()的操作 ——通常都不会用 Collection 类型来表示。
         *
         * 因此，很多你期望的支持所有集合的操作都在 Iterables 类中。大多数 Iterables 方法有一个在 Iterators 类中的对应版本，用来处理 Iterator。
         *
         * 截至 Guava 1.2 版本，Iterables 使用 FluentIterable 类进行了补充，它包装了一个 Iterable 实例，并对许多操作提供了”fluent”（链式调用）语法。
         *
         * 下面列出了一些最常用的工具方法，但更多 Iterables 的函数式方法将在第四章讨论。
         */
        /**
         *      concat(Iterable<Iterable>)	        串联多个 iterables 的懒视图*	                                            concat(Iterable...)
         *      frequency(Iterable, Object)	        返回对象在 iterable 中出现的次数	                                        与 Collections.frequency (Collection, Object)比较；Multiset
         *      partition(Iterable, int)	        把 iterable 按指定大小分割，得到的子集都不能进行修改操作	                        Lists.partition(List, int)；paddedPartition(Iterable, int)
         *      getFirst(Iterable, T default)	    返回 iterable 的第一个元素，若 iterable 为空则返回默认值	                    与Iterable.iterator(). next()比较;FluentIterable.first()
         *      getLast(Iterable)	                返回 iterable 的最后一个元素，若 iterable 为空则抛出NoSuchElementException	getLast(Iterable, T default)；FluentIterable.last()
         *      elementsEqual(Iterable, Iterable)	如果两个 iterable 中的所有元素相等且顺序一致，返回 true	                    与 List.equals(Object)比较
         *      unmodifiableIterable(Iterable)	    返回 iterable 的不可变视图	                                                与 Collections. unmodifiableCollection(Collection)比较
         *      limit(Iterable, int)	            限制 iterable 的元素个数限制给定值	                                        FluentIterable.limit(int)
         *      getOnlyElement(Iterable)	        获取 iterable 中唯一的元素，如果 iterable 为空或有多个元素，则快速失败	        getOnlyElement(Iterable, T default)
         */

        final Iterable<Integer> concat = Iterables.concat(Lists.newArrayList(Lists.newArrayList(1, 2, 3), Lists.newArrayList(4, 5, 6)));
        concat.forEach(System.out::println);
        System.out.println("=================================================================================");

        final int frequency = Iterables.frequency(Lists.newArrayList(1, 1, 2, 2, 2, 3), 1);
        System.out.println(frequency);
        System.out.println("=================================================================================");

        final Iterable<List<Integer>> partition = Iterables.partition(Lists.newArrayList(1, 1, 2, 2, 2, 3), 3);
        partition.forEach(System.out::println);
        System.out.println("=================================================================================");

        final Integer first = Iterables.getFirst(Lists.newArrayList(1, 1, 2, 2, 2, 3), 3);
        System.out.println(first);
        System.out.println("=================================================================================");

        final Integer last = Iterables.getLast(Lists.newArrayList(1, 1, 2, 2, 2, 3), 3);
        System.out.println(last);
        System.out.println("=================================================================================");

        final boolean b = Iterables.elementsEqual(Lists.newArrayList(1, 2, 3), Lists.newArrayList(1, 2, 3));
        final boolean c = Iterables.elementsEqual(Lists.newArrayList(1, 2, 3), Lists.newArrayList(1, 2, 4));
        System.out.println(b);
        System.out.println(c);
        System.out.println("=================================================================================");

        final Iterable<Integer> integers = Iterables.unmodifiableIterable(Lists.newArrayList(1, 2, 3));
        System.out.println("=================================================================================");

        final Iterable<Integer> limit = Iterables.limit(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7), 2);
        limit.forEach(System.out::println);
        System.out.println("=================================================================================");

        System.out.println(Iterables.getOnlyElement(Lists.newArrayList(1)));
//        System.out.println(Iterables.getOnlyElement(Lists.newArrayList(1,2)));
        System.out.println("=================================================================================");

        /**
         * 与 Collection 方法相似的工具方法
         *
         * 通常来说，Collection 的实现天然支持操作其他 Collection，但却不能操作 Iterable。
         *
         * 下面的方法中，如果传入的 Iterable 是一个 Collection 实例，则实际操作将会委托给相应的 Collection 接口方法。例如，往 Iterables.size 方法传入是一个 Collection 实例，它不会真的遍历 iterator 获取大小，而是直接调用 Collection.size。
         */
        /**
         *      方法	类似的 Collection                                            方法	                                        等价的 FluentIterable 方法
         *      addAll(Collection addTo, Iterable toAdd)	                    Collection.addAll(Collection)
         *      contains(Iterable, Object)	                                    Collection.contains(Object)	                FluentIterable.contains(Object)
         *      removeAll(Iterable removeFrom, Collection toRemove)	            Collection.removeAll(Collection)
         *      retainAll(Iterable removeFrom, Collection toRetain)	            Collection.retainAll(Collection)
         *      size(Iterable)	                                                Collection.size()	                        FluentIterable.size()
         *      toArray(Iterable, Class)	                                    Collection.toArray(T[])	                    FluentIterable.toArray(Class)
         *      isEmpty(Iterable)	                                            Collection.isEmpty()	                    FluentIterable.isEmpty()
         *      get(Iterable, int)	                                            List.get(int)	                            FluentIterable.get(int)
         *      toString(Iterable)	                                            Collection.toString()	                    FluentIterable.toString()
         */
        final ArrayList<Integer> collection = Lists.newArrayList(1,2,3,4,5,6);

        Iterables.addAll(collection, Iterables.concat(Lists.newArrayList(7,8)));
        System.out.println(collection);
        System.out.println("=================================================================================");

        System.out.println(Iterables.contains(collection, 1));
        System.out.println("=================================================================================");

        Iterables.removeAll(collection, Lists.newArrayList(2));
        System.out.println(collection);
        System.out.println("=================================================================================");

        Iterables.retainAll(collection, Lists.newArrayList(1));
        System.out.println(collection);
        System.out.println("=================================================================================");

        System.out.println(Iterables.size(collection));
        System.out.println("=================================================================================");

        System.out.println(Iterables.toArray(collection, Integer.class).getClass());
        System.out.println("=================================================================================");

        System.out.println(Iterables.isEmpty(collection));
        System.out.println("=================================================================================");

        System.out.println(Iterables.get(collection, 0));
        System.out.println("=================================================================================");

        System.out.println(Iterables.toString(collection));
        System.out.println("=================================================================================");


    }


    public static void test3() {

        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // partition(List, int)	把 List 按指定大小分割
        System.out.println(Lists.partition(list, 3));

        // reverse(List)	返回给定 List 的反转视图。注: 如果 List 是不可变的，考虑改用 ImmutableList.reverse()。
        System.out.println(Lists.reverse(list));

        /**
         * 静态工厂方法
         * Lists 提供如下静态工厂方法：
         *
         * 具体实现类型	工厂方法
         * ArrayList	basic, with elements, from Iterable, with exact capacity, with expected size, from Iterator
         * LinkedList	basic, from Iterable
         */

        Lists.newArrayList();
        Lists.newArrayList(1,2,3);
        Lists.newArrayList(Iterables.concat(Lists.newArrayList(1)));
        Lists.newArrayListWithCapacity(10);
        Lists.newArrayListWithExpectedSize(10);
        Lists.newArrayList(Lists.newArrayList(1).iterator());

        Lists.newLinkedList();
        Lists.newLinkedList(Iterables.concat(Lists.newArrayList(1)));
    }

    public static void test4() {
        /**
         * 方法
         * union(Set, Set) 并集
         * intersection(Set, Set) 交集
         * difference(Set, Set) 差集，前面集合差
         * symmetricDifference(Set, Set) 差集，双差
         */

        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");

        System.out.println(Sets.union(wordsWithPrimeLength, primes));
        System.out.println(Sets.intersection(wordsWithPrimeLength, primes));
        System.out.println(Sets.difference(wordsWithPrimeLength, primes));
        System.out.println(Sets.symmetricDifference(wordsWithPrimeLength, primes));

        /**
         * 其他 Set 工具方法
         * 方法	描述	另请参见
         * cartesianProduct(List<Set>)	返回所有集合的笛卡儿积	cartesianProduct(Set...)
         * powerSet(Set)	返回给定集合的所有子集
         */
        Set<String> animals = ImmutableSet.of("gerbil", "hamster");
        Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");

        Set<List<String>> product = Sets.cartesianProduct(animals, fruits);
        System.out.println(product);
        // {{"gerbil", "apple"}, {"gerbil", "orange"}, {"gerbil", "banana"},
        //  {"hamster", "apple"}, {"hamster", "orange"}, {"hamster", "banana"}}

        Set<Set<String>> animalSets = Sets.powerSet(animals);
        animalSets.forEach(System.out::println);
        // {{}, {"gerbil"}, {"hamster"}, {"gerbil", "hamster"}}

        /**
         * 静态工厂方法
         * Sets 提供如下静态工厂方法：
         *
         * 具体实现类型	        工厂方法
         * HashSet	            basic, with elements, from Iterable, with expected size, from Iterator
         * LinkedHashSet	    basic, from Iterable, with expected size
         * TreeSet	            basic, with Comparator, from Iterable
         */
        Sets.newHashSet();
        Sets.newHashSet(1,2,3);
        Sets.newHashSet(Iterables.concat(Lists.newArrayList(1)));
        Sets.newHashSetWithExpectedSize(10);
        Sets.newHashSet(Lists.newArrayList(1,2,2,2,2).iterator());

        Sets.newLinkedHashSet();
        Sets.newLinkedHashSet(Iterables.concat(Lists.newArrayList(1,2)));
        Sets.newLinkedHashSetWithExpectedSize(100);

        Sets.newTreeSet();
        Sets.newTreeSet(Iterables.concat(Lists.newArrayList(1)));
        Sets.newTreeSet((Comparator<Integer>) (o1, o2) -> 0);

    }

    public static void test5() {

        /**
         * uniqueIndex
         *
         * Maps.uniqueIndex(Iterable,Function) 通常针对的场景是：有一组对象，它们在某个属性上分别有独一无二的值，而我们希望能够按照这个属性值查找对象——译者注：这个方法返回一个 Map，键为 Function 返回的属性值，值为 Iterable 中相应的元素，因此我们可以反复用这个 Map 进行查找操作。
         *
         * 比方说，我们有一堆字符串，这些字符串的长度都是独一无二的，而我们希望能够按照特定长度查找字符串：
         *
         */
        List<String> strings = Lists.newArrayList("1", "22", "333", "4444");

        ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(strings,
                string -> string.length());
        System.out.println(stringsByIndex);

        /**
         * difference
         *
         * Maps.difference(Map, Map) 用来比较两个 Map 以获取所有不同点。该方法返回 MapDifference 对象，把不同点的维恩图分解为：
         *
         * entriesInCommon()	两个 Map 中都有的映射项，包括匹配的键与值
         * entriesDiffering()	键相同但是值不同值映射项。返回的 Map 的值类型为 MapDifference.ValueDifference，以表示左右两个不同的值
         * entriesOnlyOnLeft()	键只存在于左边 Map 的映射项
         * entriesOnlyOnRight()	键只存在于右边 Map 的映射项
         */
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("a", 1, "b", 3, "c1", 3);
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        System.out.println(diff.entriesInCommon());
        System.out.println(diff.entriesDiffering());
        System.out.println(diff.entriesOnlyOnLeft());
        System.out.println(diff.entriesOnlyOnRight());
    }

    public static void test6() {
        /**
         * 处理 BiMap 的工具方法
         * Guava 中处理 BiMap 的工具方法在 Maps 类中，因为 BiMap 也是一种 Map 实现。
         *
         * BiMap工具方法	相应的 Map 工具方法
         * synchronizedBiMap(BiMap)	Collections.synchronizedMap(Map)
         * unmodifiableBiMap(BiMap)	Collections.unmodifiableMap(Map)
         */

        Maps.synchronizedBiMap(HashBiMap.create());
        Maps.unmodifiableBiMap(HashBiMap.create());
    }

    public static void test7() {
        /**
         * 静态工厂方法
         * Maps 提供如下静态工厂方法：
         *
         *      具体实现类型	        工厂方法
         *      HashMap	            basic, from Map, with expected size
         *      LinkedHashMap	    basic, from Map
         *      TreeMap	            basic, from Comparator, from SortedMap
         *      EnumMap	            from Class, from Map
         *      ConcurrentMap：      支持所有操作	basic
         *      IdentityHashMap	    basic
         */

        Maps.newHashMap();
        Maps.newHashMap(new HashMap<>());
        Maps.newHashMapWithExpectedSize(100);

        Maps.newLinkedHashMap();
        Maps.newLinkedHashMap(new HashMap<>());

        Maps.newTreeMap();
        Maps.newTreeMap((Comparator<Integer>) (o1, o2) -> 0);
//        Maps.newTreeMap(new SortedMap<Integer, String>() {
//            //略
//        });
//        Map<Number, Number> objectObjectHashMap = Maps.newHashMap();
//        Maps.newEnumMap(objectObjectHashMap);
        System.out.println(Maps.newEnumMap(RetryType.class));


    }

    public static void test8() {
        /**
         * Multisets
         * 标准的 Collection 操作会忽略 Multiset 重复元素的个数，而只关心元素是否存在于 Multiset 中，如 containsAll 方法。为此，Multisets 提供了若干方法，以顾及 Multiset 元素的重复性：
         */
        /**
         *      方法	                                                        说明	                                                                    和 Collection 方法的区别
         *      containsOccurrences(Multiset sup, Multiset sub)	            对任意 o，如果 sub.count(o)<=super.count(o)，返回 true	                    Collection.containsAll忽略个数，而只关心 sub 的元素是否都在 super 中
         *      removeOccurrences(Multiset removeFrom, Multiset toRemove)	对 toRemove 中的重复元素，仅在 removeFrom 中删除相同个数。	                    Collection.removeAll 移除所有出现在 toRemove 的元素
         *      retainOccurrences(Multiset removeFrom, Multiset toRetain)	修改 removeFrom，以保证任意 o 都符合removeFrom.count(o)<=toRetain.count(o)	Collection.retainAll 保留所有出现在 toRetain 的元素
         *      intersection(Multiset, Multiset)	                        返回两个 multiset 的交集;	                                                没有类似方法
         */

        Multiset<String> multiset1 = HashMultiset.create();
        multiset1.add("a", 2);

        Multiset<String> multiset2 = HashMultiset.create();
        multiset2.add("a", 5);

        multiset1.containsAll(multiset2); //返回true；因为包含了所有不重复元素，
        //虽然multiset1实际上包含2个"a"，而multiset2包含5个"a"
        Multisets.containsOccurrences(multiset1, multiset2); // returns false

//        multiset2.removeOccurrences(multiset1); // multiset2 现在包含3个"a"
        multiset2.removeAll(multiset1);//multiset2移除所有"a"，虽然multiset1只有2个"a"
        multiset2.isEmpty(); // returns true

        /**
         * Multisets 中的其他工具方法还包括：
         *
         * copyHighestCountFirst(Multiset)	返回 Multiset 的不可变拷贝，并将元素按重复出现的次数做降序排列
         * unmodifiableMultiset(Multiset)	返回 Multiset 的只读视图
         * unmodifiableSortedMultiset(SortedMultiset)	返回 SortedMultiset 的只读视图
         */
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a", 3);
        multiset.add("b", 5);
        multiset.add("c", 1);

        ImmutableMultiset highestCountFirst = Multisets.copyHighestCountFirst(multiset);
        //highestCountFirst，包括它的entrySet和elementSet，按{"b", "a", "c"}排列元素
    }

    public static void test9() {
        /**
         * Multimaps
         * Multimaps 提供了若干值得单独说明的通用工具方法
         */


        /**
         * index
         *
         * 作为 Maps.uniqueIndex 的兄弟方法，[Multimaps.index(Iterable, Function)]通常针对的场景是：有一组对象，它们有共同的特定属性，我们希望按照这个属性的值查询对象，但属性值不一定是独一无二的。
         *
         * 比方说，我们想把字符串按长度分组。
         */
        ImmutableSet digits = ImmutableSet.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {
            public Integer apply(String string) {
                return string.length();
            }
        };

        ImmutableListMultimap<Integer, String> digitsByLength= Multimaps.index(digits, lengthFunction);
        /*
         *  digitsByLength maps:
         *  3 => {"one", "two", "six"}
         *  4 => {"zero", "four", "five", "nine"}
         *  5 => {"three", "seven", "eight"}
         */


        /**
         * invertFrom
         *
         * 鉴于 Multimap 可以把多个键映射到同一个值（译者注：实际上这是任何 map 都有的特性），也可以把一个键映射到多个值，反转 Multimap 也会很有用。Guava 提供了 invertFrom(Multimap toInvert,
         * Multimap dest) 做这个操作，并且你可以自由选择反转后的 Multimap 实现。
         *
         * 注：如果你使用的是 ImmutableMultimap，考虑改用 [ImmutableMultimap.inverse()]做反转。
         */
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.putAll("b", Ints.asList(2, 4, 6));
        multimap.putAll("a", Ints.asList(4, 2, 1));
        multimap.putAll("c", Ints.asList(2, 5, 3));

        TreeMultimap<Integer, String> inverse = Multimaps.invertFrom(multimap, TreeMultimap.create());
        //注意我们选择的实现，因为选了TreeMultimap，得到的反转结果是有序的
        /*
         * inverse maps:
         *  1 => {"a"}
         *  2 => {"a", "b", "c"}
         *  3 => {"c"}
         *  4 => {"a", "b"}
         *  5 => {"c"}
         *  6 => {"b"}
         */


        /**
         * forMap
         *
         * 想在 Map 对象上使用 Multimap 的方法吗？forMap(Map)把 Map 包装成 SetMultimap。这个方法特别有用，例如，与 Multimaps.invertFrom 结合使用，可以把多对一的 Map 反转为一对多的 Multimap。
         */
        Map<String, Integer> map = ImmutableMap.of("a", 1, "b", 1, "c", 2);
        SetMultimap<String, Integer> multimap1 = Multimaps.forMap(map);
        // multimap：["a" => {1}, "b" => {1}, "c" => {2}]
        Multimap<Integer, String> inverse1 = Multimaps.invertFrom(multimap1, HashMultimap.create());
        // inverse：[1 => {"a","b"}, 2 => {"c"}]
    }



















}
