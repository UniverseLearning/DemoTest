package org.example.package_09_IO;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.*;
import lombok.SneakyThrows;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
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

// ============================================参考：https://zhuanlan.zhihu.com/p/671002343   ======================
public class Class_01_IO {
    @SneakyThrows
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();


        /**
         * 字节流和字符流工具类
         *
         *      ByteStreams	                                CharStreams
         *      byte[] toByteArray(InputStream)	            String toString(Readable)
         *      N/A	                                        List<String> readLines(Readable)
         *      long copy(InputStream, OutputStream)	    long copy(Readable, Appendable)
         *      void readFully(InputStream, byte[])	        N/A
         *      void skipFully(InputStream, long)	        void skipFully(Reader, long)
         *      OutputStream nullOutputStream()	            Writer nullWriter()
         */
        ByteStreams.toByteArray(new FileInputStream("example.txt"));
        ByteStreams.copy(new FileInputStream("example.txt"), new FileOutputStream("out.txt"));
        ByteStreams.readFully(new FileInputStream("123.txt"), new byte[100]);
        ByteStreams.skipFully(new FileInputStream("456.txt"), 10012L); // 丢弃前n个字节
        ByteStreams.nullOutputStream();// 创建一个孔输出

        final ByteArrayDataInput badi = ByteStreams.newDataInput(new byte[100]);
        final ByteArrayDataOutput bado = ByteStreams.newDataOutput(3);
        bado.writeChars("我是一个单词");
        for (byte b : bado.toByteArray()) {
            System.out.println(b);
        }

        // Resource 和 Sink
        /**
         * 	    字节	        字符
         * 读	ByteSource	CharSource
         * 写	ByteSink	CharSink
         */
        /**
         * 创建 Resource 和 Sink
         * Guava 提供了若干源与汇的实现：
         *
         *      字节	                                                    字符
         *      Files.asByteSource(File)	                            Files.asCharSource(File, Charset)
         *      Files.asByteSink(File, FileWriteMode...)	            Files.asCharSink(File, Charset, FileWriteMode...)
         *      Resources.asByteSource(URL)	                            Resources.asCharSource(URL, Charset)
         *      ByteSource.wrap(byte[])	                                CharSource.wrap(CharSequence)
         *      ByteSource.concat(ByteSource...)	                    CharSource.concat(CharSource...)
         *      ByteSource.slice(long, long)	                        N/A
         *      N/A	                                                    ByteSource.asCharSource(Charset)
         *      N/A	                                                    ByteSink.asCharSink(Charset)
         */

        final CharSource charSource = Files.asCharSource(new File("123.txt"), Charsets.UTF_8);
        /**
         * 使用 Resource
         *
         *      字节源	                            字符源
         *      byte[] read()	                    String read()
         *      N/A	                                ImmutableList<String> readLines()
         *      N/A	                                String readFirstLine()
         *      long copyTo(ByteSink)	            long copyTo(CharSink)
         *      long copyTo(OutputStream)	        long copyTo(Appendable)
         *      long size() (in bytes)	            N/A
         *      boolean isEmpty()	                boolean isEmpty()
         *      boolean contentEquals(ByteSource)	N/A
         *      HashCode hash(HashFunction)	        N/A
         */
        final ImmutableList<String> strings = charSource.readLines();

        /**
         * 使用 Sink
         *
         *      字节汇	                        字符汇
         *      void write(byte[])	            void write(CharSequence)
         *      long writeFrom(InputStream)	    long writeFrom(Readable)
         *      N/A	                            void writeLines(Iterable<? extends CharSequence>)
         *      N/A	                            void writeLines(Iterable<? extends CharSequence>, String)
         */
        File file = new File("1.txt");
        //Read the lines of a UTF-8 text file
        ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
        //Count distinct word occurrences in a file
        Multiset<String> wordOccurrences = HashMultiset.create(
                Splitter.on(CharMatcher.WHITESPACE)
                        .trimResults()
                        .omitEmptyStrings()
                        .split(Files.asCharSource(file, Charsets.UTF_8).read()));

        //SHA-1 a file
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha1());

        //Copy the data from a URL to a file
        Resources.asByteSource(new URL("http://localhost:8080/a.txt")).copyTo(Files.asByteSink(file));

        /**
         * 文件操作
         *
         * 除了创建文件源和文件的方法，Files 类还包含了若干你可能感兴趣的便利方法。
         *
         * createParentDirs(File)	        必要时为文件创建父目录
         * getFileExtension(String)	        返回给定路径所表示文件的扩展名
         * getNameWithoutExtension(String)	返回去除了扩展名的文件名
         * simplifyPath(String)	            规范文件路径，并不总是与文件系统一致，请仔细测试
         * fileTreeTraverser()	            返回 TreeTraverser 用于遍历文件树
         */
        Files.createParentDirs(new File("1.txt"));
        Files.getFileExtension("C:/1.txt");
        Files.getNameWithoutExtension("C:/1.txt");
        Files.simplifyPath("C/1.txt");
        final TreeTraverser<File> fileTreeTraverser = Files.fileTreeTraverser();
    }

    @SneakyThrows
    public static void test1() {
        /**
         * 第2章：Guava I/O库概览
         *
         * 在深入挖掘之前，咱们先来了解一下Guava I/O库都有些什么东西。Guava I/O库其实是一套全面的I/O工具集，它不仅包括文件操作，还有流处理、字符集处理、还有我个人超喜欢的Source和Sink模式。听起来是不是已经很酷了？
         *
         * 那么Guava I/O和Java标准I/O操作有什么不一样的地方呢？首先，Guava的设计理念是简洁优雅。比如读写文件，Java原生可能需要好几步，还要处理异常。
         * Guava则是直接一行代码，既简单又直观。再比如流处理，Guava提供了更灵活的转换和操作方式，让流的处理更加得心应手。
         *
         * 咱们来看个简单的例子，比如说读取一个文本文件的内容。在Java原生代码中，咱们可能得这么写：
         */
        try (BufferedReader br = new BufferedReader(new FileReader("example.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 但是用Guava，就可以变得超级简单：
        List<String> lines = Files.asCharSource(new File("example.txt"), Charsets.UTF_8).readLines();
        lines.forEach(System.out::println);
        // 看到区别了吧？Guava的代码不仅更短，而且可读性也强很多。而且，Guava还考虑了很多细节，比如字符集处理，在这里咱们用的是Charsets.UTF_8，这在处理有不同编码的文件时特别有用。


    }
    @SneakyThrows
    public static void test2() {
        /**
         * 第3章：文件操作简化
         */

        // 读取文件
        File file = new File("example.txt");
        List<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
        for (String line : lines) {
            System.out.println(line);
        }

        // 写入文件
        List<String> content = Arrays.asList("Line 1", "Line 2", "Line 3");
        File file1 = new File("example-write.txt");
        Files.asCharSink(file1, Charsets.UTF_8).writeLines(content);

        // 复制文件
        File original = new File("source.txt");
        File copy = new File("destination.txt");
        Files.copy(original, copy);

        // 移动文件
        File toMove = new File("toMove.txt");
        File destination = new File("moved.txt");
        Files.move(toMove, destination);
    }
    @SneakyThrows
    public static void test3() {
        // 第4章：流处理与转换

        // 简化的流读取
        InputStream input = new FileInputStream("example.txt");
        String text = CharStreams.toString(new InputStreamReader(input, Charsets.UTF_8));
        input.close();

        // 流的转换和处理
//        FluentIterable<String> lines =
//                CharStreams.readLines(new InputStreamReader(input, Charsets.UTF_8)).transform(line -> line.toUpperCase()); // 将每一行都转换为大写
//        input.close();

        // 高效的流拷贝
        InputStream input1 = new FileInputStream("source.txt");
        OutputStream output1 = new FileOutputStream("dest.txt");
        ByteStreams.copy(input1, output1); // 将数据从input流拷贝到output流
        input1.close();
        output1.close();

    }
    public static void test4() {}
}
