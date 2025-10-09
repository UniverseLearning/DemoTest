package org.example.package_02_集合;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-11 10:02
 * @Version v2.0
 */

/**
 * 定义：
 *
 * 每个有经验的 Java 程序员都在某处实现过 Map<K, List>或 Map<K, Set>，并且要忍受这个结构的笨拙。例如，Map<K, Set>通常用来表示非标定有向图。Guava 的 Multimap 可以很容易地把一个键映射到多个值。换句话说，Multimap 是把键映射到任意多个值的一般方式。
 *
 * 可以用两种方式思考 Multimap 的概念：”键-单个值映射”的集合：
 *
 * a -> 1 a -> 2 a ->4 b -> 3 c -> 5
 *
 * 或者”键-值集合映射”的映射：
 *
 * a -> [1, 2, 4] b -> 3 c -> 5
 *
 * 一般来说，Multimap 接口应该用第一种方式看待，但 asMap()视图返回 Map<K, Collection>，让你可以按另一种方式看待 Multimap。重要的是，不会有任何键映射到空集合：一个键要么至少到一个值，要么根本就不在 Multimap 中。
 *
 * 很少会直接使用 Multimap 接口，更多时候你会用 ListMultimap 或 SetMultimap 接口，它们分别把键映射到 List 或 Set。
 */

import com.google.common.collect.HashMultimap;

import java.util.Arrays;

/**
 *  Multimap 的各种实现
 *  Multimap 提供了多种形式的实现。在大多数要使用 Map<K, Collection>的地方，你都可以使用它们：
 *
 *      实现	                    键行为类似	            值行为类似
 *      ArrayListMultimap	    HashMap	                ArrayList
 *      HashMultimap	        HashMap	                HashSet
 *      LinkedListMultimap*	    LinkedHashMap*	        LinkedList*
 *      LinkedHashMultimap**	LinkedHashMap	        LinkedHashMap
 *      TreeMultimap	        TreeMap	                TreeSet
 *      ImmutableListMultimap	ImmutableMap	        ImmutableList
 *      ImmutableSetMultimap	ImmutableMap	        ImmutableSet
 */
public class Class_02_新集合类型_MultiMap {
    public static void main(String[] args) {

        /**
         *      方法签名	                        描述	                                                                                    等价于
         *      put(K, V)	                    添加键到单个值的映射	                                                                    multimap.get(key).add(value)
         *      putAll(K, Iterable<V>)	        依次添加键到多个值的映射	                                                                Iterables.addAll(multimap.get(key), values)
         *      remove(K, V)	                移除键到值的映射；如果有这样的键值并成功移除，返回 true。     	                                multimap.get(key).remove(value)
         *      removeAll(K)	                清除键对应的所有值，返回的集合包含所有之前映射到 K 的值，但修改这个集合就不会影响 Multimap 了。	    multimap.get(key).clear()
         *      replaceValues(K, Iterable<V>)	清除键对应的所有值，并重新把 key 关联到 Iterable 中的每个元素。返回的集合包含所有之前映射到 K 的值。	multimap.get(key).clear(); Iterables.addAll(multimap.get(key), values)
         */

        HashMultimap<Object, Object> multimap = HashMultimap.create();

        multimap.put("a", 1);
        multimap.put("a", 2);
        System.out.println(multimap);
        multimap.putAll("b", Arrays.asList(1,2,3,4));
        System.out.println(multimap);
        multimap.remove("a", 1);
        System.out.println(multimap);
        multimap.removeAll("b");
        System.out.println(multimap);
        multimap.replaceValues("a", Arrays.asList(1,5,6,7));
        System.out.println(multimap);


        /**
         * [asMap]为 Multimap<K, V>提供 Map<K,Collection>形式的视图。返回的 Map 支持 remove 操作，并且会反映到底层的 Multimap，但它不支持 put 或 putAll 操作。更重要的是，如果你想为 Multimap 中没有的键返回 null，而不是一个新的、可写的空集合，你就可以使用 asMap().get(key)。（你可以并且应当把 asMap.get(key)返回的结果转化为适当的集合类型——如 SetMultimap.asMap.get(key)的结果转为 Set，ListMultimap.asMap.get(key)的结果转为 List——Java 类型系统不允许 ListMultimap 直接为 asMap.get(key)返回 List——译者注：也可以用 Multimaps 中的 asMap 静态方法帮你完成类型转换）
         * [entries]用 Collection<Map.Entry<K, V>>返回 Multimap 中所有”键-单个值映射”——包括重复键。（对 SetMultimap，返回的是 Set）
         * [keySet]用 Set 表示 Multimap 中所有不同的键。
         * [keys]用 Multiset 表示 Multimap 中的所有键，每个键重复出现的次数等于它映射的值的个数。可以从这个 Multiset 中移除元素，但不能做添加操作；移除操作会反映到底层的 Multimap。
         * [values()]用一个”扁平”的Collection包含 Multimap 中的所有值。这有一点类似于 Iterables.concat(multimap.asMap().values())，但它直接返回了单个 Collection，而不像 multimap.asMap().values()那样是按键区分开的 Collection。
         */
        multimap.asMap();
        multimap.entries();
        multimap.keySet();
        multimap.keys();
        multimap.values();

        /**
         * Multimap 不是 Map
         * Multimap<K, V>不是 Map<K,Collection>，虽然某些 Multimap 实现中可能使用了 map。它们之间的显著区别包括：
         *
         *      1.Multimap.get(key)总是返回非 null、但是可能空的集合。这并不意味着 Multimap 为相应的键花费内存创建了集合，而只是提供一个集合视图方便你为键增加映射值
         *          ——译者注：如果有这样的键，返回的集合只是包装了 Multimap 中已有的集合；如果没有这样的键，返回的空集合也只是持有 Multimap 引用的栈对象，让你可以用来操作底层的 Multimap。因此，返回的集合不会占据太多内存，数据实际上还是存放在 Multimap 中。
         *      2.如果你更喜欢像 Map 那样，为 Multimap 中没有的键返回 null，请使用 asMap()视图获取一个 Map<K, Collection>。
         *          （或者用静态方法 Multimaps.asMap()为 ListMultimap 返回一个 Map<K, List>。对于 SetMultimap 和 SortedSetMultimap，也有类似的静态方法存在）
         *      3.当且仅当有值映射到键时，Multimap.containsKey(key)才会返回 true。尤其需要注意的是，如果键 k 之前映射过一个或多个值，但它们都被移除后，Multimap.containsKey(key)会返回 false。
         *      4.Multimap.entries()返回 Multimap 中所有”键-单个值映射”——包括重复键。如果你想要得到所有”键-值集合映射”，请使用 asMap().entrySet()。
         *      5.Multimap.size()返回所有”键-单个值映射”的个数，而非不同键的个数。要得到不同键的个数，请改用 Multimap.keySet().size()。
         */
        multimap = HashMultimap.create();
        multimap.putAll("a", Arrays.asList(1,2));
        multimap.putAll("b", Arrays.asList(1,2,3,4));
        System.out.println("==================================");
        System.out.println(multimap.get("c"));
        System.out.println(multimap.asMap().get("c"));
        System.out.println(multimap.containsKey("a"));
        System.out.println(multimap.containsValue(1));
        System.out.println(multimap.containsEntry("a", 1));

        System.out.println(multimap.entries());
        System.out.println(multimap.asMap().entrySet());

        System.out.println(multimap.size());
        System.out.println(multimap.keySet().size());


    }
}
