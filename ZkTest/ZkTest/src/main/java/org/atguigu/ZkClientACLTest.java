package org.atguigu;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
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
public class ZkClientACLTest {

    private static final String CONNECT_STRING = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private static final int SESSION_TIMEOUT = 5000; // 5秒

    private ZooKeeper zk;

    public static void main(String[] args) {
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

//        // 等待连接建立
//        while (zk.getState() != ZooKeeper.States.CONNECTED) {
//            Thread.sleep(100);
//        }

        System.out.println("ZooKeeper client connected.");
    }


    // ==================== 1. 创建节点 ====================

    /**
     * 示例 1：使用 world:anyone（开放权限）
     */
    @Test
    public void demo1() throws KeeperException, InterruptedException {
        // Ids.OPEN_ACL_UNSAFE 就是 world:anyone + ALL 权限
        List<ACL> acl = Ids.OPEN_ACL_UNSAFE; // 等价于：new ArrayList<>(Arrays.asList(new ACL(Perms.ALL, new Id("world", "anyone"))));

        zk.create("/public-node", "hello".getBytes(), acl, CreateMode.PERSISTENT);
    }
    /**
     * 示例 2：使用 digest（用户名+密码保护）
     */
    @Test
    public void demo2() throws NoSuchAlgorithmException, InterruptedException, KeeperException {
        String userPass = "alice:123456";
        String digest = DigestAuthenticationProvider.generateDigest(userPass);
        System.out.println(digest); // 输出：alice:base64(SHA1("alice:123456"))
        // 例如：alice:6Vd7pJkTqL5x8zYwN3mR2sQ1oP0nM9lK8jH7gF6eD5c=


        // 步骤2 创建受保护节点
        String digestAcl = digest; // 替换为实际值
        Id id = new Id("digest", digestAcl);
        ACL acl = new ACL(ZooDefs.Perms.ALL, id);
        List<ACL> aclList = Arrays.asList(acl);

//        zk.create("/secure-node", "secret".getBytes(), aclList, CreateMode.PERSISTENT);


        // 步骤3 其他客户端访问时需先认证
        // 必须先添加认证信息！
        zk.addAuthInfo("digest", "alice:123456".getBytes());
        // 现在可以读取
        byte[] data = zk.getData("/secure-node", false, null);
        System.out.println(new String(data)); // 输出：secret


    }
    /**
     * 示例 3：混合 ACL（多个用户不同权限）
     */
    @Test
    public void demo3() throws NoSuchAlgorithmException, InterruptedException, KeeperException {
        List<ACL> acls = new ArrayList<>();

        // alice: 全权限
        acls.add(new ACL(ZooDefs.Perms.ALL, new Id("digest", "alice:xxx")));

        // bob: 只读
        acls.add(new ACL(ZooDefs.Perms.READ, new Id("digest", "bob:yyy")));

        // 本地 IP 可读写
        acls.add(new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, new Id("ip", "10.88.100.14")));

        zk.create("/multi-user-node", "data".getBytes(), acls, CreateMode.PERSISTENT);
//        zk.delete("/multi-user-node", 0);


    }


}
