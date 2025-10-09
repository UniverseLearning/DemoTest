package org.example.p_04_JSONPath;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-29 10:30
 * @Version v2.0
 */

/**
 * 1. JSONPath介绍
 * fastjson 1.2.0之后的版本支持JSONPath。这是一个很强大的功能，可以在java框架中当作对象查询语言（OQL）来使用。
 *
 * 2. API
 *      //  求值，静态方法
 *      public static Object eval(Object rootObject, String path);
 *
 *      // 计算Size，Map非空元素个数，对象非空元素个数，Collection的Size，数组的长度。其他无法求值返回-1
 *      public static int size(Object rootObject, String path);
 *
 *      // 是否包含，path中是否存在对象
 *      public static boolean contains(Object rootObject, String path) { }
 *
 *      // 是否包含，path中是否存在指定值，如果是集合或者数组，在集合中查找value是否存在
 *      public static boolean containsValue(Object rootObject, String path, Object value) { }
 *
 *      // 修改制定路径的值，如果修改成功，返回true，否则返回false
 *      public static boolean set(Object rootObject, String path, Object value) {}
 *
 *      // 在数组或者集合中添加元素
 *      public static boolean array_add(Object rootObject, String path, Object... values);
 *
 * 3. 支持语法
 *=========================================================================================================================================================================
 * JSONPATH	                                                            描述
 *      $	                                                            根对象，例如$.name
 *      [num]	                                                        数组访问，其中num是数字，可以是负数。例如$[0].leader.departments[-1].name
 *      [num0,num1,num2...]	                                            数组多个元素访问，其中num是数字，可以是负数，返回数组中的多个元素。例如$[0,3,-2,5]
 *      [start:end]	                                                    数组范围访问，其中start和end是开始小表和结束下标，可以是负数，返回数组中的多个元素。例如$[0:5]
 *      [start:end :step]	                                            数组范围访问，其中start和end是开始小表和结束下标，可以是负数；step是步长，返回数组中的多个元素。例如$[0:5:2]
 *      [?(key)]	                                                    对象属性非空过滤，例如$.departs[?(name)]
 *      [key > 123]	                                                    数值类型对象属性比较过滤，例如$.departs[id >= 123]，比较操作符支持=,!=,>,>=,<,<=
 *      [key = '123']	                                                字符串类型对象属性比较过滤，例如$.departs[name = '123']，比较操作符支持=,!=,>,>=,<,<=
 *      [key like 'aa%']	                                            字符串类型like过滤，
 *                                                                          例如$.departs[name like 'sz*']，通配符只支持%
 *                                                                          支持not like
 *      [key rlike 'regexpr']	                                        字符串类型正则匹配过滤，
 *                                                                          例如departs[name like 'aa(.)*']，
 *                                                                          正则语法为jdk的正则语法，支持not rlike
 *      [key in ('v0', 'v1')]	                                        IN过滤, 支持字符串和数值类型
 *                                                                      例如:
 *                                                                          $.departs[name in ('wenshao','Yako')]
 *                                                                          $.departs[id not in (101,102)]
 *      [key between 234 and 456]	                                    BETWEEN过滤, 支持数值类型，支持not between
 *                                                                      例如:
 *                                                                          $.departs[id between 101 and 201]
 *                                                                          $.departs[id not between 101 and 201]
 *      length() 或者 size()	                                            数组长度。例如$.values.size()
 *                                                                          支持类型java.util.Map和java.util.Collection和数组
 *      .	                                                            属性访问，例如$.name
 *      ..	                                                            deepScan属性访问，例如$..name
 *      *	                                                            对象的所有属性，例如$.leader.*
 *      ['key']	                                                        属性访问。例如$['name']
 *      ['key0','key1']	                                                多个属性访问。例如$['id','name']
 *
 *      ======================================================================================================================================================================
 *
 *      以下两种写法的语义是相同的：
 *              $.store.book[0].title
 *              $['store']['book'][0]['title']
 *
 *
 *     4. 语法示例
 *      JSONPath	    语义
 *          $	        根对象
 *          $[-1]	    最后元素
 *          $[:-2]	    第1个至倒数第2个
 *          $[1:]	    第2个之后所有元素
 *          $[1,2,3]	集合中1,2,3个元素
 */
public class Main {
}
