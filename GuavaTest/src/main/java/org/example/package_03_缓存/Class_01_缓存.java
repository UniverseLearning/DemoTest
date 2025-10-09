package org.example.package_03_缓存;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-10 17:44
 * @Version v2.0
 */
public class Class_01_缓存 {

    @SneakyThrows
    public static void main(String[] args) {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(Integer.MAX_VALUE)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build();

        cache.put("123","123123value");
        cache.put("123","123123value1");
        cache.put("123","123123value2");

        TimeUnit.SECONDS.sleep(5);

        System.out.println(cache.getIfPresent("123"));

        TimeUnit.SECONDS.sleep(6);

        System.out.println(cache.getIfPresent("123"));
    }
}
