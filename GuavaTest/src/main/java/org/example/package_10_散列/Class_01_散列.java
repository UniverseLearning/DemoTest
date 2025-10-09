package org.example.package_10_散列;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.google.common.hash.Hashing;
import lombok.Data;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-10 17:47
 * @Version v2.0
 */
public class Class_01_散列 {

    public static void main(String[] args) {
        /**
         * Guava Hash(散列)指的是通过某种算法把数据源通过一系列的转换生成一串字符串。
         * 常见的例如hash code生成，加密字符的生成，检验字符的生成等等。接下来我们就对Guava Hash(散列)的使用做一个介绍。
         * 使用很简单。Guava Hash(散列)里面也给我们提供了很多hash算法。已经能满足我们大部分需求了。
         *
         * Hash里面比较重要的类有：Hashing、HashFunction、Hasher、HashCode、Funnel、PrimitiveSink。
         *
         * Hashing类是一个帮助类，里面提供的方法都是根据不同的hash算法生成对应的HashFunction对象。每个hash算法都实现了HashFunction。Hashing类里面提供的方法如下。
         *      [ goodFastHash(int minimumBits) ]: 返回一个多用途的，临时使用的，非加密的 Hash Function
         *      [ murmur3_32(int seed) ]: 使用指定参数值做种子返回一个 murmur3 算法实现的 32 位的哈希值
         *      [ murmur3_32() ]: 使用零值种子返回一个 murmur3 算法实现的 32 位的哈希值
         *      [ murmur3_128() ]: 使用零值种子返回一个 murmur3 算法实现的128位的哈希值。
         *      [ sipHash24() ]: sipHash24 加密算法
         *      [ sipHash24(long k0, long k1) ]: sipHash24 加密算法
         *      [ md5() ]: md5 加密算法。
         *      [ sha1() ]：sha1算法，hash。
         *      [ sha256() ]: sha256 加密算法
         *      [ sha384() ]: sha384 加密算法
         *      [ sha512() ]: sha512 加密算法
         *      [ hmacMd5(Key key) ]： hmacMd5 加密算法
         *      [ hmacMd5(byte[] key) ]：hmacMd5 加密算法
         *      [ hmacSha1(Key key) ]：hmacSha1 加密算法
         *      [ hmacSha1(byte[] key) ]：hmacSha1 加密算法
         *      [ hmacSha256(Key key) ]：hmacSha256 加密算法
         *      [ hmacSha256(byte[] key) ]：hmacSha256 加密算法
         *      [ hmacSha512(Key key) ]：hmacSha512 加密算法
         *      [ hmacSha512(byte[] key) ]：hmacSha512 加密算法
         *      [ crc32c() ]：CRC32C 校验算法
         *      [ crc32() ]：CRC-32 校验算法
         *      [ adler32() ]：Adler-32 校验算法
         *      [ farmHashFingerprint64() ]
         *      [ consistentHash(HashCode hashCode, int buckets) ]：以给定 buckets 的大小分配一致性哈希，最大限度的减少随 buckets 增长而进行重新映射的需要。
         *      [ consistentHash(long input, int buckets) ]
         *      [ combineOrdered(Iterable hashCodes) ]：以有序的方式使 HashCode 结合起来，如果两个 HashCode 集合采用此种方法结合后的 HashCode 相同，那么这两个 HashCode 集合中的元素可能是顺序相等的。
         *      [ combineUnordered(Iterable hashCodes) ]: 以无序的方式使 HashCode 结合起来，如果两个 HashCode 集合采用此方法结合后的 HashCode 相同，那么这两个 HashCode 集合中的元素可能在某种排序方式下是顺序相等的。
         *      [ concatenating(HashFunction first, HashFunction second, HashFunction… rest) ]: 将多个hash值拼接在一起。比如你想要1024位的一个hash，你就可以Hashing.concatenating(Hashing.sha512(), Hashing.sha512())
         */

        /**
         * HashFunction:  HashFunction有两个作用：创建Hasher对象、也可以直接根据输入条件返回HashCode结果。HashFunction里面常用函数如下：
         *
         * Hasher:  Hasher的主要作用是根据加入的数据源得到HashCode。数据源通过提供的putXxx（）方法添加、hash()方法返回计算结果HashCode。
         *
         * HashCode:  HashCode是对结果的一个封装。
         *
         * Funnel、PrimitiveSink:   定义了怎样将一个Object对象里面的各个属性放到PrimitiveSink里面，Hasher的putObject方法需要传入这样的对象。
         */

        Funnel<Person> personFunnel = (person, into) -> into
                .putInt(person.id)
                .putString(person.firstName, Charsets.UTF_8)
                .putString(person.lastName, Charsets.UTF_8)
                .putInt(person.birthYear);

        // 各种算法对应的hash code
        String input = "hello, world";
        // MD5
        System.out.println(Hashing.md5().hashBytes(input.getBytes()).toString());
        // sha256
        System.out.println(Hashing.sha256().hashBytes(input.getBytes()).toString());
        // sha512
        System.out.println(Hashing.sha512().hashBytes(input.getBytes()).toString());
        // crc32
        System.out.println(Hashing.crc32().hashBytes(input.getBytes()).toString());
        // MD5
        System.out.println(Hashing.md5().hashUnencodedChars(input).toString());

    }

    @Data
    static class Person {
        private Integer id;

        private String firstName;

        private String lastName;

        private Integer birthYear;
    }
}
