package org.example.package_11_事件总线;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.SneakyThrows;

import javax.swing.event.ChangeEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

/**
 *      EventBus术语	        解释	                                备注
 *      事件(消息)	        可以向事件总线发布的对象	            通常是一个类，不同的消息事件用不同的类来代替，消息内容就是类里面的属性
 *      订阅	                向事件总线注册监听者以接受事件的行为	    EventBus.register(Object)，参数就是监听者
 *      监听者	            提供一个处理方法，希望接受和处理事件的对象	通常也是一个类，里面有消息的处理方法
 *      处理方法	            监听者提供的公共方法，事件总线使用该方法向监听者发送事件；该方法应该用Subscribe注解	监听者里面添加了Subscribe注解的方法，就可以认为是消息的处理方法
 *      发布消息	            通过事件总线向所有匹配的监听者提供事件	EventBus.post(Object)
 *
 * 作者：tuacy
 * 链接：https://www.jianshu.com/p/2d62f3b46af9
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Class_01_事件总线 {
    public static void main(String[] args) {

        /**
         *          public EventBus() { // 创建EventBus对象, identifier为“default”
         *             this("default");
         *         }
         *
         *         public EventBus(String identifier); // 创建EventBus对象, identifier就是用一个字符串来标识EventBus
         *
         *         public EventBus(SubscriberExceptionHandler exceptionHandler); // 创建EventBus对象，exceptionHandler是异常处理类(消息处理过程中有异常，统一处理)
         *
         *         public final String identifier(); // EventBus对象标识
         *
         *         public void unregister(Object object); // 取消监听者的注册
         *
         *         public void post(Object event); // 发布一个消息
         */

//        test1();
        test2();
    }

    @SneakyThrows
    private static void test1() {
        // 定义一个EventBus对象，因为我这里是测试，才这样写的。实际你应该定义一个单例获取其他的方式
        EventBus eventBus = new EventBus("test");
        // 注册监听者
        eventBus.register(new OrderEventListener());
        // 发布消息
        eventBus.post(new OrderMessage("消息1"));

        TimeUnit.SECONDS.sleep(10);
    }

    @SneakyThrows
    private static void test2() {
        // AsyncEventBus是继承EventBus异步消息，准确来讲是我们可以指定消息的处理在哪里执行。比如我们可以把他们都放到一个线程池里面去执行。

        AsyncEventBus aeb = new AsyncEventBus(Executors.newSingleThreadExecutor());
        aeb.register(new OrderEventListener());
        aeb.post(new OrderMessage("消息1"));

    }

    static class OrderMessage {
        /**
         * 命令对应的内容
         */
        private String orderContent;

        public OrderMessage(String orderContent) {
            this.orderContent = orderContent;
        }

        public String getOrderContent() {
            return orderContent;
        }
        public void setOrderContent(String orderContent) {
            this.orderContent = orderContent;
        }
    }

    static class OrderEventListener {
        /**
         * 如果发送了OrderMessage消息，会进入到该函数的处理
         * @param event 消息
         */
        @Subscribe
        public void dealWithEvent(OrderMessage event) {
            // TODO: 收到EventTest消息之后，做相应的处理
            System.out.println("我收到了您的命令，命令内容为：" + event.getOrderContent() + ", 当前线程：" + Thread.currentThread().getName());
        }
    }


}
