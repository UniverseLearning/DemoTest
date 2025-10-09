package org.example.package_02_集合;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-11 10:33
 * @Version v2.0
 */

/**
 * BiMap 的各种实现
 *      键–值实现	        值–键实现	            对应的BiMap实现
 *      HashMap	            HashMap	                HashBiMap
 *      ImmutableMap	    ImmutableMap	        ImmutableBiMap
 *      EnumMap	            EnumMap	                EnumBiMap
 *      EnumMap	            HashMap	                EnumHashBiMap
 */
public class Class_02_新集合类型_BiMap {

    public static void main(String[] args) {
        /**
         * 传统上，实现键值对的双向映射需要维护两个单独的 map，并保持它们间的同步。但这种方式很容易出错，而且对于值已经在 map 中的情况，会变得非常混乱。例如：
         */
        Map<String, Integer> nameToId = Maps.newHashMap();
        Map<Integer, String> idToName = Maps.newHashMap();

        nameToId.put("Bob", 42);
        idToName.put(42, "Bob");
        //如果"Bob"和42已经在map中了，会发生什么?
        //如果我们忘了同步两个map，会有诡异的bug发生...


        /**
         * BiMap<K, V>是特殊的 Map：
         *
         *      1.可以用 inverse()反转 BiMap<K, V>的键值映射
         *      2.保证值是唯一的，因此 values()返回 Set 而不是普通的 Collection
         * 在 BiMap 中，如果你想把键映射到已经存在的值，会抛出 IllegalArgumentException 异常。如果对特定值，你想要强制替换它的键，请使用 BiMap.forcePut(key, value)。
         */
        final HashBiMap<Object, Object> biMap = HashBiMap.create();
        biMap.put("a", 1);
        biMap.put("a", 2);
//        biMap.put("b", 1);
//        biMap.put("b", 2);
        biMap.put("b", 3);
//        biMap.put("v", 3);
        System.out.println(biMap);
        System.out.println(biMap.inverse());


    }
}
