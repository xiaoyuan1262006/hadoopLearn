package com.gwmfc.lxy.learn.hdfs.entityBean;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @ProjectName: hadoopLearn
 * @Package: com.gwmfc.lxy.learn.hdfs.entityBean
 * @ClassName: JoinBean
 * @Author: liuxiaoyuan
 * @Description: ${description}
 * @Date: 05/09/2019 13:31
 * @Version: 1.0
 */
public class JoinBean implements Writable {

    private String orderId;

    private String userId;

    private String userName;

    private int age;

    private String friend;

    private String tableName;

    public void set(String orderId, String userId, String userName, int age, String friend,String tableName) {
        this.orderId=orderId;
        this.userId=userId;
        this.userName=userName;
        this.age=age;
        this.friend=friend;
        this.tableName=tableName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId=orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId=userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName=userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend=friend;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName=tableName;
    }

    @Override
    public String toString() {
        return this.orderId+","+this.userId+","+this.userName+","+this.age+","+this.friend;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.orderId);
        dataOutput.writeUTF(this.userId);
        dataOutput.writeUTF(this.userName);
        dataOutput.writeInt(this.age);
        dataOutput.writeUTF(this.friend);
        dataOutput.writeUTF(this.tableName);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.orderId=dataInput.readUTF();
        this.userId=dataInput.readUTF();
        this.userName=dataInput.readUTF();
        this.age=dataInput.readInt();
        this.friend=dataInput.readUTF();
        this.tableName=dataInput.readUTF();
    }
}
