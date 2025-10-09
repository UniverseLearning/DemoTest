package org.example.package_02_集合;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-11 9:34
 * @Version v2.0
 */

/**
 *      方法	                        描述
 *      count(E)	                给定元素在 Multiset 中的计数
 *      elementSet()	            Multiset 中不重复元素的集合，类型为 Set<E>
 *      entrySet()	                和 Map 的 entrySet 类似，返回 Set<Multiset.Entry<E>>，其中包含的 Entry 支持 getElement()和 getCount()方法
 *      add(E, int)	                增加给定元素在 Multiset 中的计数
 *      remove(E, int)	            减少给定元素在 Multiset 中的计数
 *      setCount(E, int)	        设置给定元素在 Multiset 中的计数，不可以为负数
 *      size()	                    返回集合元素的总个数（包括重复的元素）
 */

/**
 *   Guava 提供了多种 Multiset 的实现，大致对应 JDK 中 Map 的各种实现：
 *      Map	                    对应的Multiset	                是否支持null元素
 *      HashMap	                HashMultiset	                是
 *      TreeMap	                TreeMultiset	                是（如果 comparator 支持的话）
 *      LinkedHashMap	        LinkedHashMultiset	            是
 *      ConcurrentHashMap	    ConcurrentHashMultiset	        否
 *      ImmutableMap	        ImmutableMultiset	            否
 */
public class Class_02_新集合类型_MultiSet {
    public static void main(String[] args) {
        /**
         * 概念：Guava 提供了一个新集合类型 Multiset，它可以多次添加相等的元素。
         * 维基百科从数学角度这样定义 Multiset：”集合[set]概念的延伸，它的元素可以重复出现…与集合[set]相同而与元组[tuple]相反的是，Multiset 元素的顺序是无关紧要的：Multiset {a, a, b}和{a, b, a}是相等的”。
         * ——译者注：这里所说的集合[set]是数学上的概念，Multiset继承自 JDK 中的 Collection 接口，而不是 Set 接口，所以包含重复元素并没有违反原有的接口契约。
         */


        /**
         * 例子：统计一个词在文档中出现了多少次，传统的做法是这样的
         */
        String[] words = {"a","a","b","c","d","d","d"};
        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String word : words) {
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word, 1);
            } else {
                counts.put(word, count + 1);
            }
        }
        /**
         * 例子：统计一个词在文档中出现了多少次，新的的做法是这样的
         */
        final HashMultiset<String> multiset = HashMultiset.create(Arrays.asList(words));
        final int a = multiset.count("a");
        System.out.println(a);
        final Set<String> elementSet = multiset.elementSet();
        System.out.println(elementSet);
        final Set<Multiset.Entry<String>> entrySet = multiset.entrySet();
        System.out.println(entrySet);
        multiset.add("a",10);
        System.out.println(multiset.entrySet());
        multiset.remove("a", 4);
        System.out.println(multiset.entrySet());
        multiset.setCount("a", 1);
        System.out.println(multiset.entrySet());

    }
}
