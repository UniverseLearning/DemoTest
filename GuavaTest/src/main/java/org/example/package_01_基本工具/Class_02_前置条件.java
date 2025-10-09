package org.example.package_01_基本工具;

import com.google.common.base.Preconditions;

import java.util.prefs.Preferences;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-10 17:41
 * @Version v2.0
 */
public class Class_02_前置条件 {
    public static void main(String[] args) {

        /**
         * 前置条件：让方法调用的前置条件判断更简单。
         *
         * Guava 在 Preconditions 类中提供了若干前置条件判断的实用方法，我们强烈建议在 Eclipse 中静态导入这些方法。每个方法都有三个变种：
         *
         *      1.没有额外参数：抛出的异常中没有错误消息；
         *      2.有一个 Object 对象作为额外参数：抛出的异常使用 Object.toString() 作为错误消息；
         *      3.有一个 String 对象作为额外参数，并且有一组任意数量的附加 Object 对象：这个变种处理异常消息的方式有点类似 printf，但考虑 GWT 的兼容性和效率，只支持%s 指示符。例如：
         */
        int i = 1;
        int j = 2;
        Preconditions.checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        Preconditions.checkArgument(i < j, "Expected i < j, but %s > %s", i, j);

        /**
         *      方法声明（不包括额外参数）	                            描述	                                                                    检查失败时抛出的异常
         *      checkArgument(boolean)	                            检查 boolean 是否为 true，用来检查传递给方法的参数。	                        IllegalArgumentException
         *      checkNotNull(T)	                                    检查 value 是否为 null，该方法直接返回 value，因此可以内嵌使用 checkNotNull。	NullPointerException
         *      checkState(boolean)	                                用来检查对象的某些状态。	                                                IllegalStateException
         *      checkElementIndex(int index, int size)	            检查 index 作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size *	IndexOutOfBoundsException
         *      checkPositionIndex(int index, int size)	            检查 index 作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size *	IndexOutOfBoundsException
         *      checkPositionIndexes(int start, int end, int size)	检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效*	                IndexOutOfBoundsException
         */
    }
}
