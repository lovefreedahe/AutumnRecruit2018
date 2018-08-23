package com.wangrupeng.zookeeper.client;

import org.apache.curator.CuratorConnectionLossException;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.storm.shade.org.apache.commons.exec.ExecuteException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class ZKClient {
    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.1.5:2181", retryPolicy);
        client.start();
        //createNode(client, "/wrp", CreateMode.PERSISTENT, "");
        //createNode(client, "/wrp/temp", CreateMode.EPHEMERAL, "tempNode");
        System.out.println(getNodeType(client, "/wrp"));
        System.out.println(getNodeType(client, "/wrp/temp"));
    }

    public static boolean createNode(CuratorFramework client, String path, CreateMode createMode, String data) {
        try {
            client.create().withMode(createMode).forPath(path, data.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static CreateMode getNodeType(CuratorFramework client, String path) {
        try {
            client.sync(); //确保获取最新数据
            Stat stat = client.checkExists().forPath(path);
            if(stat == null) {
                return null;
            }

            if (stat.getEphemeralOwner() > 0) {
                return CreateMode.EPHEMERAL;
            }
            return CreateMode.PERSISTENT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
