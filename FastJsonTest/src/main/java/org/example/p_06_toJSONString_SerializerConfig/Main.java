package org.example.p_06_toJSONString_SerializerConfig;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-31 9:47
 * @Version v2.0
 */

/**
 * 定义 fastjson 全局配置
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        // 1.单例模式获取，全局唯一
        SerializeConfig config = SerializeConfig.getGlobalInstance();

        // 2.初始化 java 基础类型的 序列化器、反序列化器
        /**
         * private void initSerializers() {
         *         put(Boolean.class, BooleanCodec.instance);
         *         put(Character.class, CharacterCodec.instance);
         *         put(Byte.class, IntegerCodec.instance);
         *         put(Short.class, IntegerCodec.instance);
         *         put(Integer.class, IntegerCodec.instance);
         *         put(Long.class, LongCodec.instance);
         *         put(Float.class, FloatCodec.instance);
         *         put(Double.class, DoubleSerializer.instance);
         *         put(BigDecimal.class, BigDecimalCodec.instance);
         *         put(BigInteger.class, BigIntegerCodec.instance);
         *         put(String.class, StringCodec.instance);
         *         put(byte[].class, PrimitiveArraySerializer.instance);
         *         put(short[].class, PrimitiveArraySerializer.instance);
         *         put(int[].class, PrimitiveArraySerializer.instance);
         *         put(long[].class, PrimitiveArraySerializer.instance);
         *         put(float[].class, PrimitiveArraySerializer.instance);
         *         put(double[].class, PrimitiveArraySerializer.instance);
         *         put(boolean[].class, PrimitiveArraySerializer.instance);
         *         put(char[].class, PrimitiveArraySerializer.instance);
         *         put(Object[].class, ObjectArrayCodec.instance);
         *         put(Class.class, MiscCodec.instance);
         *
         *         put(SimpleDateFormat.class, MiscCodec.instance);
         *         put(Currency.class, new MiscCodec());
         *         put(TimeZone.class, MiscCodec.instance);
         *         put(InetAddress.class, MiscCodec.instance);
         *         put(Inet4Address.class, MiscCodec.instance);
         *         put(Inet6Address.class, MiscCodec.instance);
         *         put(InetSocketAddress.class, MiscCodec.instance);
         *         put(File.class, MiscCodec.instance);
         *         put(Appendable.class, AppendableSerializer.instance);
         *         put(StringBuffer.class, AppendableSerializer.instance);
         *         put(StringBuilder.class, AppendableSerializer.instance);
         *         put(Charset.class, ToStringSerializer.instance);
         *         put(Pattern.class, ToStringSerializer.instance);
         *         put(Locale.class, ToStringSerializer.instance);
         *         put(URI.class, ToStringSerializer.instance);
         *         put(URL.class, ToStringSerializer.instance);
         *         put(UUID.class, ToStringSerializer.instance);
         *
         *         // atomic
         *         put(AtomicBoolean.class, AtomicCodec.instance);
         *         put(AtomicInteger.class, AtomicCodec.instance);
         *         put(AtomicLong.class, AtomicCodec.instance);
         *         put(AtomicReference.class, ReferenceCodec.instance);
         *         put(AtomicIntegerArray.class, AtomicCodec.instance);
         *         put(AtomicLongArray.class, AtomicCodec.instance);
         *
         *         put(WeakReference.class, ReferenceCodec.instance);
         *         put(SoftReference.class, ReferenceCodec.instance);
         *
         *         put(LinkedList.class, CollectionCodec.instance);
         *     }
         */

        // 3.添加 类级别的Filter
        config.addFilter(Integer.class, new NameFilter() {
            @Override
            public String process(Object object, String name, Object value) {
                return null;
            }
        });

        // 4.配置类级别的Feature
        config.config(Integer.class, SerializerFeature.BrowserCompatible, true);

        // 5.获取指定类的序列化器
        config.getObjectWriter(Integer.class);
        config.getObjectWriter(Integer.class, true);

        // 6.设置指定类的序列化器
        config.put(Integer.class, JSONSerializer.class);

    }
}
