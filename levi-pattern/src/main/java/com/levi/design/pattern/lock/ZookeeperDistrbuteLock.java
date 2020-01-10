package com.levi.design.pattern.lock;

import org.I0Itec.zkclient.IZkDataListener;

/**
 * @author jianghaihui
 * @date 2019/12/27 14:50
 */
public class ZookeeperDistrbuteLock extends  ZookeeperAbstractLock {

    @Override
    void waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {

            // 节点被删除
            @Override
            public void handleDataDeleted(String arg0) throws Exception {


            }

            // 节点被修改
            public void handleDataChange(String arg0, Object arg1) throws Exception {

            }
        };
    }

    @Override
    boolean tryLock() {
        try{
            zkClient.createEphemeral(lockPath);
            return true;
        }catch (Exception e){
            //如果失败，直接catch
            return false;
        }

    }
}
