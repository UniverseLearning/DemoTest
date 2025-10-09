package org.example.package_01_基本工具;

import com.google.common.base.Throwables;

import java.io.IOException;
import java.sql.SQLException;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2024-05-10 17:42
 * @Version v2.0
 */
public class Class_05_异常捕获 {
    public static void main(String[] args) throws IOException, SQLException {

        try {
            someMethodThatCouldThrowAnything();
        } catch (IKnowWhatToDoWithThisException e) {
            handle(e);
        } catch (Throwable t) {
            Throwables.propagateIfInstanceOf(t, IOException.class);
            Throwables.propagateIfInstanceOf(t, SQLException.class);
            throw Throwables.propagate(t);
        }

        /**
         * RuntimeException propagate(Throwable)
         *      如果 Throwable 是 Error 或 RuntimeException，直接抛出；否则把 Throwable 包装成 RuntimeException 抛出。返回类型是 RuntimeException，所以你可以像上面说的那样写成throw Throwables.propagate(t)，Java 编译器会意识到这行代码保证抛出异常。
         * void propagateIfInstanceOf( Throwable, Class<X extends Exception>) throws X
         *      Throwable 类型为 X 才抛出
         * void propagateIfPossible( Throwable)
         *      Throwable 类型为 Error 或 RuntimeException 才抛出
         * void propagateIfPossible( Throwable, Class<X extends Throwable>) throws X
         *      Throwable 类型为 X, Error 或 RuntimeException 才抛出
         */
    }

    private static void handle(IKnowWhatToDoWithThisException e) {
    }

    private static void someMethodThatCouldThrowAnything() throws IKnowWhatToDoWithThisException {
    }

    class IKnowWhatToDoWithThisException extends Exception {

    }




}
