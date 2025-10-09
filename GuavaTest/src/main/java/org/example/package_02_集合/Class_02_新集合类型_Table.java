package org.example.package_02_集合;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-11 10:42
 * @Version v2.0
 */

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import sun.security.provider.certpath.Vertex;

/**
 * 通常来说，当你想使用多个键做索引的时候，你可能会用类似 Map<FirstName, Map<LastName, Person>>的实现，这种方式很丑陋，使用上也不友好。
 * Guava 为此提供了新集合类型 Table，它有两个支持所有类型的键：”行”和”列”。Table 提供多种视图，以便你从各种角度使用它：
 */

/**
 * Table 有如下几种实现：
 *
 * HashBasedTable：本质上用 HashMap<R, HashMap<C, V>>实现；
 * TreeBasedTable：本质上用 TreeMap<R, TreeMap<C,V>>实现；
 * ImmutableTable：本质上用 ImmutableMap<R, ImmutableMap<C, V>>实现；注：ImmutableTable 对稀疏或密集的数据集都有优化。
 * ArrayTable：要求在构造时就指定行和列的大小，本质上由一个二维数组实现，以提升访问速度和密集 Table 的内存利用率。ArrayTable 与其他 Table 的工作原理有点不同，请参见 Javadoc 了解详情。
 */
public class Class_02_新集合类型_Table {
    public static void main(String[] args) {

        Table<String, String, Integer> weightedGraph = HashBasedTable.create();
        weightedGraph.put("v1", "v2", 4);
        weightedGraph.put("v1", "v3", 20);
        weightedGraph.put("v2", "v3", 5);
        System.out.println(weightedGraph);

        weightedGraph.row("v1"); // returns a Map mapping v2 to 4, v3 to 20
        weightedGraph.column("v3"); // returns a Map mapping v1 to 20, v2 to 5

        /**
         * [rowMap()]：用 Map<R, Map<C, V>>表现 Table<R, C, V>。同样的，
         * [rowKeySet()]返回”行”的集合Set。
         * row(r) ：用 Map<C, V>返回给定”行”的所有列，对这个 map 进行的写操作也将写入 Table 中。
         * 类似的列访问方法：[columnMap()]、[columnKeySet()]、[column(c)]。（基于列的访问会比基于的行访问稍微低效点）
         * [cellSet()]：用元素类型为 Table.Cell<R, C, V>的 Set 表现 Table<R, C, V>。Cell 类似于 Map.Entry，但它是用行和列两个键区分的。
         */
        System.out.println(weightedGraph.rowMap());
        System.out.println(weightedGraph.rowKeySet());
        System.out.println(weightedGraph.columnKeySet());
        System.out.println(weightedGraph.row("v1"));
        System.out.println(weightedGraph.column("v3"));
        System.out.println(weightedGraph.cellSet());



    }


}
