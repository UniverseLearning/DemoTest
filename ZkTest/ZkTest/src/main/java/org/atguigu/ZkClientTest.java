package org.atguigu;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.ZooDefs.Ids;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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
public class ZkClientTest {

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

//    /**
//     * 清理测试数据并关闭连接
//     */
//    public void cleanup() {
//        if (zk != null) {
//            try {
//                deleteRecursively(BASE_PATH); // 递归删除测试根节点
//                zk.close();
//                System.out.println("ZooKeeper client closed.");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * 递归删除节点（用于清理）
     */
//    private void deleteRecursively(String path) throws KeeperException, InterruptedException {
//        if (path.equals("/") || !exists(path, false)) return;
//
//        List<String> children = getChildren(path, false);
//        for (String child : children) {
//            deleteRecursively(path + "/" + child);
//        }
//        delete(path, -1); // -1 表示忽略版本
//    }

    /**
     * 执行所有测试用例
     */
    public void runAllTests() throws Exception {
        init();

        // ====== 1. 创建节点 (Create) ======
        testCreate();
        testCreateAsync();

        // ====== 2. 读取节点 (Exists, GetData, GetChildren) ======
        testExists();
        testExistsAsync();
        testGetData();
        testGetDataAsync();
        testGetChildren();
        testGetChildrenWithStat();

        // ====== 3. 更新节点 (SetData) ======
        testSetData();
        testSetDataAsync();

        // ====== 4. 删除节点 (Delete) ======
        testDelete();
        testDeleteAsync();

        // ====== 5. ACL 权限管理 ======
        testGetACL();
        testSetACL();

        // ====== 6. 多操作 (Multi) ======
        testMulti();

        // ====== 7. 事务 (Transaction) ======
        testTransaction();

        // ====== 8. Watcher 监听机制 ======
        testWatcher();

        // ====== 9. 会话与连接信息 ======
        testSessionInfo();

        // ====== 10. 其他辅助方法 ======
        testSync();
        testAddAuth();
        testRegisterDefaultWatcher();
    }

    // ==================== 1. 创建节点 ====================

    /**
     * 测试同步创建节点
     * 支持持久、临时、顺序节点等模式
     */
    @Test
    public void testCreate() throws KeeperException, InterruptedException {
        String path1 = BASE_PATH + "/node1";
        String createdPath = zk.create(path1, "data1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("Created persistent node: " + createdPath);

        String path2 = BASE_PATH + "/ephemeral";
        zk.create(path2, "temp".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Created ephemeral node: " + path2);

        String seqPath = zk.create(BASE_PATH + "/seq-", "seq".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("Created sequential node: " + seqPath); // 如 /test/seq-0000000001

        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 测试异步创建节点
     */
    @Test
    public void testCreateAsync() throws InterruptedException {
        AsyncCallback.StringCallback callback = (rc, path, ctx, name) -> {
            System.out.println("[Async Create] rc=" + rc + ", path=" + path + ", name=" + name);
        };

        zk.create(BASE_PATH + "/async-node", "async".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, callback, "ctx-create");
        Thread.sleep(1000); // 等待异步回调
    }

    // ==================== 2. 读取节点 ====================

    /**
     * 测试同步 exists：检查节点是否存在
     */
    @Test
    public void testExists() throws KeeperException, InterruptedException {
        Stat stat = zk.exists(BASE_PATH + "/node1", false); // false 表示不注册 watcher
        if (stat != null) {
            System.out.println("Node exists, czxid=" + stat.getCzxid() + ", version=" + stat.getVersion());
        }
    }

    /**
     * 测试异步 exists
     */
    @Test
    public void testExistsAsync() throws InterruptedException {
        AsyncCallback.StatCallback callback = (rc, path, ctx, stat) -> {
            System.out.println("[Async Exists] rc=" + rc + ", path=" + path + ", stat=" + (stat != null ? stat.getVersion() : "null"));
        };
        zk.exists(BASE_PATH + "/node1", false, callback, "ctx-exists");
        Thread.sleep(1000);
    }

    /**
     * 测试同步 getData：获取节点数据和状态
     */
    @Test
    public void testGetData() throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        byte[] data = zk.getData(BASE_PATH + "/node1", false, stat);
        System.out.println("Data: " + new String(data) + ", version=" + stat.getVersion());
    }

    /**
     * 测试异步 getData
     */
    @Test
    public void testGetDataAsync() throws InterruptedException {
        AsyncCallback.DataCallback callback = (rc, path, ctx, data, stat) -> {
            System.out.println("[Async GetData] rc=" + rc + ", data=" + (data != null ? new String(data) : "null"));
        };
        zk.getData(BASE_PATH + "/node1", false, callback, "ctx-getdata");
        Thread.sleep(1000);
    }

    /**
     * 测试同步 getChildren：获取子节点列表
     */
    @Test
    public void testGetChildren() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren(BASE_PATH, false);
        System.out.println("Children of " + BASE_PATH + ": " + children);
    }

    /**
     * 测试同步 getChildren2：同时获取子节点列表和父节点 Stat
     */
    @Test
    public void testGetChildrenWithStat() throws KeeperException, InterruptedException {
        Stat parentStat = new Stat();
        List<String> children = zk.getChildren(BASE_PATH, false, parentStat);
        System.out.println("Parent version: " + parentStat.getVersion() + ", children: " + children);
    }

    // ==================== 3. 更新节点 ====================

    /**
     * 测试同步 setData：更新节点数据
     */
    @Test
    public void testSetData() throws KeeperException, InterruptedException {
        Stat stat = zk.setData(BASE_PATH + "/node1", "updated-data".getBytes(), -1); // -1 忽略版本
        System.out.println("SetData success, new version: " + stat.getVersion());
    }

    /**
     * 测试异步 setData
     */
    @Test
    public void testSetDataAsync() throws InterruptedException {
        AsyncCallback.StatCallback callback = (rc, path, ctx, stat) -> {
            System.out.println("[Async SetData] rc=" + rc + ", new version=" + (stat != null ? stat.getVersion() : "null"));
        };
        zk.setData(BASE_PATH + "/node1", "async-update".getBytes(), -1, callback, "ctx-setdata");
        Thread.sleep(1000);
    }

    // ==================== 4. 删除节点 ====================

    /**
     * 测试同步 delete
     */
    @Test
    public void testDelete() throws KeeperException, InterruptedException {
        // 先创建一个可删除的节点
        String delPath = BASE_PATH + "/to-delete";
        zk.create(delPath, "del".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.delete(delPath, -1);
        System.out.println("Deleted node: " + delPath);
    }

    /**
     * 测试异步 delete
     */
    @Test
    public void testDeleteAsync() throws InterruptedException, KeeperException {
        String delPath = BASE_PATH + "/async-to-delete";
        zk.create(delPath, "async-del".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        AsyncCallback.VoidCallback callback = (rc, path, ctx) -> {
            System.out.println("[Async Delete] rc=" + rc + ", path=" + path);
        };
        zk.delete(delPath, -1, callback, "ctx-delete");
        Thread.sleep(1000);
    }

    // ==================== 5. ACL 权限管理 ====================

    /**
     * 测试 getACL：获取节点 ACL 列表
     */
    @Test
    public void testGetACL() throws KeeperException, InterruptedException {
        List<ACL> aclList = zk.getACL(BASE_PATH + "/node1", new Stat());
        System.out.println("ACL list size: " + aclList.size());
        for (ACL acl : aclList) {
            System.out.println("  Scheme: " + acl.getId().getScheme() + ", ID: " + acl.getId().getId());
        }
    }

    /**
     * 测试 setACL：设置节点 ACL
     */
    @Test
    public void testSetACL() throws KeeperException, InterruptedException {
        // 创建一个只有特定用户可读的 ACL
        List<ACL> aclList = new ArrayList<>();
        aclList.add(new ACL(ZooDefs.Perms.READ, new Id("digest", "user:base64-encoded-pw")));
        aclList.add(new ACL(ZooDefs.Perms.ALL, Ids.ANYONE_ID_UNSAFE));

        Stat stat = zk.setACL(BASE_PATH + "/node1", aclList, -1);
        System.out.println("SetACL success, version: " + stat.getVersion());
    }

    // ==================== 6. 多操作 (Multi) ====================

    /**
     * 测试 multi：原子性执行多个操作
     */
    @Test
    public void testMulti() throws KeeperException, InterruptedException {
        List<Op> ops = new ArrayList<>();
        ops.add(Op.create(BASE_PATH + "/multi1", "m1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT));
        ops.add(Op.create(BASE_PATH + "/multi2", "m2".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT));
        ops.add(Op.setData(BASE_PATH + "/node1", "multi-update".getBytes(), -1));

        List<OpResult> results = zk.multi(ops);
        System.out.println("Multi operation results: " + results.size() + " ops executed.");
    }

    // ==================== 7. 事务 (Transaction) ====================

    /**
     * 测试 transaction：构建式多操作
     */
    @Test
    public void testTransaction() throws KeeperException, InterruptedException {
        Transaction tx = zk.transaction();
        tx.create(BASE_PATH + "/tx1", "tx1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
                .create(BASE_PATH + "/tx2", "tx2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
                .setData(BASE_PATH + "/node1", "tx-update".getBytes(), -1)
                .commit(); // commit 返回 List<OpResult>

        System.out.println("Transaction committed.");
    }

    // ==================== 8. Watcher 监听机制 ====================

    /**
     * 测试 Watcher 监听节点变化
     */
    @Test
    public void testWatcher() throws KeeperException, InterruptedException {
        String watchPath = BASE_PATH + "/watched-node";
        zk.create(watchPath, "init".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 注册 Watcher
        Stat stat = zk.exists(watchPath, event -> {
            System.out.println("[Watcher] Path: " + event.getPath() +
                    ", Type: " + event.getType() +
                    ", State: " + event.getState());
            // 注意：此处 Watcher 已失效，如需持续监听需重新注册
        });

        // 触发事件
        zk.setData(watchPath, "changed".getBytes(), stat.getVersion());
        Thread.sleep(1000); // 等待事件触发
    }

    // ==================== 9. 会话与连接信息 ====================

    /**
     * 测试会话相关信息
     */
    @Test
    public void testSessionInfo() {
        long sessionId = zk.getSessionId();
        byte[] sessionPasswd = zk.getSessionPasswd();
        int timeout = zk.getSessionTimeout();
        ZooKeeper.States state = zk.getState();

        System.out.println("Session ID: 0x" + Long.toHexString(sessionId));
        System.out.println("Session Timeout: " + timeout + " ms");
        System.out.println("Connection State: " + state);
    }

    // ==================== 10. 其他辅助方法 ====================

    /**
     * 测试 sync：强制刷新客户端视图（通常不需要显式调用）
     */
    @Test
    public void testSync() throws InterruptedException {
        AsyncCallback.VoidCallback callback = (rc, path, ctx) -> {
            System.out.println("[Sync] rc=" + rc);
        };
        zk.sync(BASE_PATH, callback, "ctx-sync");
        Thread.sleep(1000);
    }

    /**
     * 测试 addAuthInfo：添加认证信息（如 digest）
     */
    @Test
    public void testAddAuth() {
        // 示例：添加 digest 认证（实际密码需加密）
        // zk.addAuthInfo("digest", "user:password".getBytes());
        System.out.println("addAuthInfo: demonstrated (commented out for safety)");
    }

    /**
     * 测试 register：替换默认 Watcher
     */
    @Test
    public void testRegisterDefaultWatcher() {
        Watcher newWatcher = event -> {
            if (event.getType() == Watcher.Event.EventType.None) {
                System.out.println("[New Default Watcher] Connection state: " + event.getState());
            }
        };
        zk.register(newWatcher);
        System.out.println("Default watcher registered.");
    }
}
