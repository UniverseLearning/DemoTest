package org.example.package_07_原生类型;

import com.google.common.collect.Lists;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedInts;

import java.util.Comparator;
import java.util.List;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-10 17:46
 * @Version v2.0
 */
public class Class_01_原生类型 {

    public static void main(String[] args) {
        /**
         *      原生类型	        Guava 工具类（都在 com.google.common.primitives 包）
         *      byte	        Bytes, SignedBytes, UnsignedBytes
         *      short	        Shorts
         *      int	            Ints, UnsignedInteger, UnsignedInts
         *      long	        Longs, UnsignedLong, UnsignedLongs
         *      float	        Floats
         *      double	        Doubles
         *      char	        Chars
         *      boolean	        Booleans
         */
//        test1();
//        test2();
//        test3();
        test4();

    }

    public static void test1() {
        // 原生类型数组工具

        /**
         *          方法签名	                                        描述	                                            类似方法	                                        可用性
         *          List<Wrapper> asList(prim… backingArray)	    把数组转为相应包装类的 List	                        Arrays.asList	                                符号无关*
         *          prim[] toArray(Collection<Wrapper> collection)	把集合拷贝为数组，和 collection.toArray()一样线程安全	Collection.toArray()	                        符号无关
         *          prim[] concat(prim[]… arrays)	                串联多个原生类型数组	                            Iterables.concat	                            符号无关
         *          boolean contains(prim[] array, prim target)	    判断原生类型数组是否包含给定值	                    Collection.contains	                            符号无关
         *          int indexOf(prim[] array, prim target)	        给定值在数组中首次出现处的索引，若不包含此值返回-1	    List.indexOf	                                符号无关
         *          int lastIndexOf(prim[] array, prim target)	    给定值在数组最后出现的索引，若不包含此值返回-1	        List.lastIndexOf	                            符号无关
         *          prim min(prim… array)	                        数组中最小的值	                                    Collections.min	                                符号相关*
         *          prim max(prim… array)	                        数组中最大的值	                                    Collections.max	                                符号相关
         *          String join(String separator, prim… array)	    把数组用给定分隔符连接为字符串	                    Joiner.on(separator).join	                    符号相关
         *          Comparator<prim[]> lexicographicalComparator()	按字典序比较原生类型数组的Comparator	                Ordering.natural().lexicographical()            符号相关
         */
        //*符号无关方法存在于 Bytes, Shorts, Ints, Longs, Floats, Doubles, Chars, Booleans。而UnsignedInts, UnsignedLongs, SignedBytes, 或 UnsignedBytes 不存在。
        //*符号相关方法存在于 SignedBytes, UnsignedBytes, Shorts, Ints, Longs, Floats, Doubles, Chars, Booleans, UnsignedInts, UnsignedLongs。而 Bytes 不存在。
        final List<Byte> byteList = Bytes.asList((byte) 1, (byte) 2);
        final byte[] array = Bytes.toArray(Lists.newArrayList(1, 2, 3));
        Bytes.concat(new byte[]{}, new byte[]{1,2});
        Bytes.contains(new byte[]{}, ((byte) 1));
        Bytes.indexOf(new byte[]{}, ((byte) 1));
        Bytes.lastIndexOf(new byte[]{}, ((byte) 1));

        Ints.asList(1,2,3);
        final int[] array1 = Ints.toArray(Lists.newArrayList(1, 2, 3));
        Ints.concat(new int[4]);
        Ints.contains(new int[5], 1);
        Ints.indexOf(new int[5], 1);
        Ints.lastIndexOf(new int[5], 1);
        Ints.min(new int[4]);
        Ints.max(new int[4]);
        final String join = Ints.join(",", new int[5]);
        final Comparator<int[]> comparator = Ints.lexicographicalComparator();
    }

    public static void test2() {
        // 通用工具方法
        /**
         * Guava 为原生类型提供了若干 JDK6 没有的工具方法。但请注意，其中某些方法已经存在于 JDK7 中。
         *
         *      方法签名	                        描述	                                                                            可用性
         *      int compare(prim a, prim b)	    传统的 Comparator.compare 方法，但针对原生类型。JDK7 的原生类型包装类也提供这样的方法	    符号相关
         *      prim checkedCast(long value)	把给定 long 值转为某一原生类型，若给定值不符合该原生类型，则抛出 IllegalArgumentException	仅适用于符号相关的整型*
         *      prim saturatedCast(long value)	把给定 long 值转为某一原生类型，若给定值不符合则使用最接近的原生类型值	                    仅适用于符号相关的整型
         */
        //*这里的整型包括 byte, short, int, long。不包括 char, boolean, float, 或 double。
        //**译者注：不符合主要是指 long 值超出 prim 类型的范围，比如过大的 long 超出 int 范围。
        //注：com.google.common.math.DoubleMath 提供了舍入 double 的方法，支持多种舍入模式。相见第 12 章的”浮点数运算”。
        final int compare = Ints.compare(1, 2);
        final int i = Ints.checkedCast(100L);
        final int i1 = Ints.saturatedCast(11);
    }

    public static void test3() {
        // 字节转换方法
        // Guava 提供了若干方法，用来把原生类型按大字节序与字节数组相互转换。所有这些方法都是符号无关的，此外 Booleans 没有提供任何下面的方法。
        /**
         *      方法或字段签名	                            描述
         *      int BYTES	                            常量：表示该原生类型需要的字节数
         *      prim fromByteArray(byte[] bytes)	    使用字节数组的前 Prims.BYTES 个字节，按大字节序返回原生类型值；如果 bytes.length <= Prims.BYTES，抛出 IAE
         *      prim fromBytes(byte b1, …, byte bk)	    接受 Prims.BYTES 个字节参数，按大字节序返回原生类型值
         *      byte[] toByteArray(prim value)	        按大字节序返回 value 的字节数组
         */

        final byte[] byteArray = Ints.toByteArray(1990);
        final int i = Ints.fromByteArray(byteArray);
        System.out.println(i);

    }

    public static void test4() {
        /**
         * 无符号通用工具方法
         * JDK 的原生类型包装类提供了有符号形式的类似方法。
         *
         *  方法签名	                                                                                                                    说明
         *  int UnsignedInts.parseUnsignedInt(String)
         *  long UnsignedLongs.parseUnsignedLong(String)	                                                                                    按无符号十进制解析字符串
         *  int UnsignedInts.parseUnsignedInt(String string, int radix)
         *  long UnsignedLongs.parseUnsignedLong(String string, int radix)	                                                            按无符号的特定进制解析字符串
         *  String UnsignedInts.toString(int)
         *  String UnsignedLongs.toString(long)	                                                                                        数字按无符号十进制转为字符串
         *  String UnsignedInts.toString(int value, int radix)
         *  String UnsignedLongs.toString(long value, int radix)	                                                                    数字按无符号特定进制转为字符串
         */

        System.out.println(UnsignedInts.parseUnsignedInt("100"));
        System.out.println(UnsignedInts.parseUnsignedInt("100", 2));

//        System.out.println(UnsignedInts.parseUnsignedInt("-100"));

        System.out.println(UnsignedInts.toString(99, 8));


    }

    public static void test5() {
        // 无符号包装类
        /**
         * 无符号包装类包含了若干方法，让使用和转换更容易。
         *  UnsignedPrim 指各种无符号包装类，如 UnsignedInteger、UnsignedLong。
         *  方法签名	                                                                    说明
         *  UnsignedPrim add(UnsignedPrim), subtract, multiply, divide, remainder	    简单算术运算
         *  UnsignedPrim valueOf(BigInteger)	                                        按给定 BigInteger 返回无符号对象，若 BigInteger 为负或不匹配，抛出 IAE
         *  UnsignedPrim valueOf(long)	                                                按给定 long 返回无符号对象，若 long 为负或不匹配，抛出 IAE
         *  UnsignedPrim asUnsigned(prim value)	                                        把给定的值当作无符号类型。例如，UnsignedInteger.asUnsigned(1<<31)的值为 231,尽管 1<<31 当作 int 时是负的
         *  BigInteger bigIntegerValue()	                                            用 BigInteger 返回该无符号对象的值
         *  toString(), toString(int radix)	                                            返回无符号值的字符串表示
         */


    }
}
