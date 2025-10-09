package org.example.package_06_字符串处理;

import com.google.common.base.*;
import com.google.common.collect.Lists;

import java.io.UnsupportedEncodingException;

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
public class Class_01_字符串处理_分割_连接_填充 {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
//        test4();
//        test5();
    }

    public static void test1() {
        // 用分隔符把字符串序列连接起来也可能会遇上不必要的麻烦。如果字符串序列中含有 null，那连接操作会更难。Fluent 风格的 Joiner 让连接字符串更简单。
        Joiner joiner = Joiner.on("; ").skipNulls();
        System.out.println(joiner.join("Harry", null, "Ron", "Hermione"));

        // 另外，useForNull(String)方法可以给定某个字符串来替换 null，而不像 skipNulls()方法是直接忽略 null。
        // Joiner 也可以用来连接对象类型，在这种情况下，它会把对象的 toString()值连接起来。
        Joiner joiner1 = Joiner.on("; ").useForNull("111");
        System.out.println(joiner1.join("Harry", null, "Ron", "Hermione"));

        // 警告：joiner 实例总是不可变的。用来定义 joiner 目标语义的配置方法总会返回一个新的 joiner 实例。这使得 joiner 实例都是线程安全的，你可以将其定义为 static final常量。
    }
    public static void test2() {
        // 拆分器[Splitter]
        /**
         * 拆分器[Splitter]
         * JDK 内建的字符串拆分工具有一些古怪的特性。比如，String.split 悄悄丢弃了尾部的分隔符。 问题：”,a,,b,”.split(“,”)返回？
         *
         *    1.  “”, “a”, “”, “b”, “”
         *    2.  null, “a”, null, “b”, null
         *    3.  “a”, null, “b”
         *    4.  “a”, “b”
         *    5.  以上都不对
         * 正确答案是 5：””, “a”, “”, “b”。只有尾部的空字符串被忽略了。 Splitter 使用令人放心的、直白的流畅 API 模式对这些混乱的特性作了完全的掌控。
         */
        final Iterable<String> split = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux");
        System.out.println(Lists.newArrayList(split));

        // 拆分器工厂
        /**
         *      方法	                        描述	                                            范例
         *      Splitter.on(char)	        按单个字符拆分	                                    Splitter.on(‘;’)
         *      Splitter.on(CharMatcher)	按字符匹配器拆分	                                Splitter.on(CharMatcher.BREAKING_WHITESPACE)
         *      Splitter.on(String)	        按字符串拆分	                                    Splitter.on(“, “)
         *      Splitter.on(Pattern)
         *      Splitter.onPattern(String)	按正则表达式拆分	                                Splitter.onPattern(“\r?\n”)
         *      Splitter.fixedLength(int)	按固定长度拆分；最后一段可能比给定长度短，但不会为空。	Splitter.fixedLength(3)
         */
        System.out.println(Splitter.on(',').split("foo,bar,,   qux"));
        System.out.println(Splitter.on(CharMatcher.BREAKING_WHITESPACE).split("foo\tbar\t\tqux"));
        System.out.println(Splitter.on("___").split("foo___bar_,   qux"));
        System.out.println(Splitter.onPattern("\r?\n").split("foo___bar_,   qux"));
        System.out.println(Splitter.fixedLength(3).split("foo___bar_,   qux"));

        // 拆分器修饰符
        //      方法	                        描述
        //      omitEmptyStrings()	        从结果中自动忽略空字符串
        //      trimResults()	            移除结果字符串的前导空白和尾部空白
        //      trimResults(CharMatcher)	给定匹配器，移除结果字符串的前导匹配字符和尾部匹配字符
        //      limit(int)	                限制拆分出的字符串数量
        final Iterable<String> split1 = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .limit(2)
                .split("foo,bar,,   qux");
        Lists.newArrayList(split1).forEach(s -> {
            System.out.println("======");
            System.out.println(s);
        });

    }
    public static void test3() {
        // 字符匹配器[CharMatcher]
        String string = "123";
        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string); //移除control字符
        String theDigits = CharMatcher.DIGIT.retainFrom(string); //只保留数字字符
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');
        //去除两端的空格，并把中间的连续空格替换成单个空格
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"); //用*号替换所有数字
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);

        //获取字符匹配器
        //CharMatcher 中的常量可以满足大多数字符匹配需求：
        //
        // ANY
        // NONE
        // WHITESPACE
        // BREAKING_WHITESPACE
        // INVISIBLE
        // DIGIT
        // JAVA_LETTER
        // JAVA_DIGIT
        // JAVA_LETTER_OR_DIGIT
        // JAVA_ISO_CONTROL
        // JAVA_LOWER_CASE
        // JAVA_UPPER_CASE
        // ASCII
        // SINGLE_WIDTH

        //其他获取字符匹配器的常见方法包括：
        //
        //  方法	                    描述
        //  anyOf(CharSequence)	    枚举匹配字符。如 CharMatcher.anyOf(“aeiou”)匹配小写英语元音
        //  is(char)	            给定单一字符匹配。
        //  inRange(char, char)	    给定字符范围匹配，如 CharMatcher.inRange(‘a’, ‘z’)
        final CharMatcher aeiouf = CharMatcher.anyOf("aeiouf");
        final CharMatcher l = CharMatcher.is('l');
        final CharMatcher charMatcher = CharMatcher.inRange('a', 'z');

        System.out.println(aeiouf.removeFrom("sdlfkjlsjdfoiwejfeeeeeeeee"));
        System.out.println(charMatcher.removeFrom("ALFDJLFKJlfskjdflkajdslfj94949"));


        // 使用字符匹配器
        //  CharMatcher 提供了多种多样的方法操作 CharSequence 中的特定字符。其中最常用的罗列如下：
        //
        //      方法	                                        描述
        //      collapseFrom(CharSequence, char)	        把每组连续的匹配字符替换为特定字符。如 WHITESPACE.collapseFrom(string, ‘ ‘)把字符串中的连续空白字符替换为单个空格。
        //      matchesAllOf(CharSequence)	                测试是否字符序列中的所有字符都匹配。
        //      removeFrom(CharSequence)	                从字符序列中移除所有匹配字符。
        //      retainFrom(CharSequence)	                在字符序列中保留匹配字符，移除其他字符。
        //      trimFrom(CharSequence)	                    移除字符序列的前导匹配字符和尾部匹配字符。
        //      replaceFrom(CharSequence, CharSequence)	    用特定字符序列替代匹配字符。

        System.out.println(charMatcher.collapseFrom("abab1a1b1abab1ababab", 'a'));
        System.out.println(charMatcher.matchesAllOf("1m1m1m1m"));
        System.out.println(charMatcher.replaceFrom("aaaa", "09"));

    }
    public static void test4() {
        // 字符集[Charsets]

        // 不要这样做字符集处理：
        try {
            byte[] bytes = "string".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // how can this possibly happen?
            throw new AssertionError(e);
        }
        // 试试这样写：
        byte[] bytes = "string".getBytes(Charsets.UTF_8);

    }
    public static void test5() {
        // 大小写格式[CaseFormat]
        // CaseFormat 被用来方便地在各种 ASCII 大小写规范间转换字符串——比如，编程语言的命名规范。CaseFormat 支持的格式如下：

        /**
         *  格式	                        范例
         *  LOWER_CAMEL	                lowerCamel
         *  LOWER_HYPHEN	            lower-hyphen
         *  LOWER_UNDERSCORE	        lower_underscore
         *  UPPER_CAMEL	                UpperCamel
         *  UPPER_UNDERSCORE	        UPPER_UNDERSCORE
         */
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME")); // returns "constantName"

    }


}
