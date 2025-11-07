package org.atguigu;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |    AI 生成的zk client 测试类             |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @Author Administrator
 * @Date 2025-11-07 14:37
 * @Version v2.0
 */
public class ZkClientWatcherTest {

    private static final String CONNECT_STRING = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private static final int SESSION_TIMEOUT = 5000; // 5秒
    private static final String BASE_PATH = "/test-zk-client";

    private ZooKeeper zk;

    public static void main(String[] args) {
//        ZkClientTest test = new ZkClientTest();
//        try {
//            test.runAllTests();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            test.cleanup();
//        }
    }

    /**
     * 初始化 ZooKeeper 客户端连接
     */
    @Before
    public void init() throws IOException, InterruptedException, KeeperException {
        // 使用默认 Watcher 监听连接状态
        Watcher connectionWatcher = event -> {
            if (event.getType() == Watcher.Event.EventType.None) {
                System.out.println("Connection state changed: " + event.getState());
            }
        };

        zk = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, connectionWatcher);

        // 等待连接建立
        while (zk.getState() != ZooKeeper.States.CONNECTED) {
            Thread.sleep(100);
        }

//        if (null == zk.exists(BASE_PATH , false)) {
//        }

        System.out.println("ZooKeeper client connected.");
    }


    /**
     * 执行所有测试用例
     */
    // ==================== 1. exists() 注册 Watcher ====================
    /**
     * 使用 exists() 注册 Watcher
     * 可监听：NodeCreated, NodeDeleted, NodeDataChanged
     */
    @Test
    public void demoExistsWatcher() throws KeeperException, InterruptedException {
        String path = "/watcher-exists";

        // 注册 Watcher（即使节点不存在也能注册！）
        Stat stat = zk.exists(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("[exists Watcher] Path: " + event.getPath() +
                        ", Type: " + event.getType() +
                        ", State: " + event.getState());

                // 重要：如需持续监听，必须重新注册！
                try {
                    if (event.getType() != Event.EventType.None) {
                        zk.exists(event.getPath(), this); // 重新注册
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (stat == null) {
            System.out.println("Node /watcher-exists does not exist yet.");
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

    // ==================== 2. getData() 注册 Watcher ====================
    /**
     * 使用 getData() 注册 Watcher
     * 可监听：NodeDeleted, NodeDataChanged
     * 要求节点必须存在！
     */
    @Test
    public void demoGetDataWatcher() throws KeeperException, InterruptedException {
        String path = "/watcher-data";

        // 先创建节点（因为 getData 要求节点存在）
        if (zk.exists(path, false) == null) {
            zk.create(path, "init".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // 注册 Watcher
        zk.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("[getData Watcher] Path: " + event.getPath() +
                        ", Type: " + event.getType());

                try {
                    if (event.getType() == Event.EventType.NodeDataChanged) {
                        // 重新获取数据并重新注册
                        byte[] data = zk.getData(event.getPath(), this, null);
                        System.out.println("  New data: " + new String(data));
                    } else if (event.getType() == Event.EventType.NodeDeleted) {
                        System.out.println("  Node deleted!");
                        // 节点已删，无法再注册 getData Watcher
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, null);

        Thread.sleep(Integer.MAX_VALUE);
    }

    // ==================== 3. getChildren() 注册 Watcher ====================
    /**
     * 使用 getChildren() 注册 Watcher
     * 可监听：NodeDeleted, NodeChildrenChanged
     * 要求父节点必须存在！
     */
    @Test
    public void demoGetChildrenWatcher() throws KeeperException, InterruptedException {
        String parentPath = "/watcher-parent";

        // 创建父节点
        if (zk.exists(parentPath, false) == null) {
            zk.create(parentPath, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // 注册 Watcher
        zk.getChildren(parentPath, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("[getChildren Watcher] Path: " + event.getPath() +
                        ", Type: " + event.getType());

                try {
                    if (event.getType() == Event.EventType.NodeChildrenChanged) {
                        List<String> children = zk.getChildren(event.getPath(), this);
                        System.out.println("  New children: " + children);
                    } else if (event.getType() == Event.EventType.NodeDeleted) {
                        System.out.println("  Parent node deleted!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(Integer.MAX_VALUE);
    }

    // ==================== 触发事件进行测试 ====================
    @Test
    public void triggerEvents() throws KeeperException, InterruptedException {
        System.out.println("\n--- Triggering events ---");

        // 触发 exists Watcher: 创建节点
        zk.create("/watcher-exists", "created".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

//        // 触发 getData Watcher: 修改数据
//        zk.setData("/watcher-data", "updated".getBytes(), -1);
//
//        // 触发 getChildren Watcher: 添加子节点
//        zk.create("/watcher-parent/child1", new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        zk.create("/watcher-parent/child2", new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 删除节点（可触发多个 Watcher）
        // zk.delete("/watcher-data", -1);
    }
}
