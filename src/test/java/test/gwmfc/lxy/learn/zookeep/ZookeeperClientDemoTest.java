package test.gwmfc.lxy.learn.zookeep;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ProjectName: hadoopLearn
 * @Package: test.gwmfc.lxy.learn.zookeep
 * @ClassName: ZookeeperClientDemoTest
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 29/09/2019 09:47
 * @Version: 1.0
 */
public class ZookeeperClientDemoTest {
    ZooKeeper zooKeeper;

    @Before
    public void setUp() throws Exception {
        zooKeeper=new ZooKeeper("master:2181,slave01:2181,slave02:2181", 2000, null);
    }


    @Test
    public void testCreat() throws Exception {
        String s=zooKeeper.create("/idea", "hello idea".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(s);
        zooKeeper.close();
    }

    @Test
    public void testUpdate() throws Exception {
        Stat stat=zooKeeper.setData("/idea", "hello idea2".getBytes(), -1);
        System.out.println(stat);
        zooKeeper.close();
    }


    @Test
    public void testGet() throws Exception {
        byte[] data=zooKeeper.getData("/idea", false, null);
        System.out.println(new String(data));
        zooKeeper.close();
    }

    @Test
    public void testListChildren() throws Exception {
        List<String> childrens=zooKeeper.getChildren("/idea", false);
        for (String child : childrens) {
            System.out.println(child);
        }
        zooKeeper.close();
    }
}