package com.liujj.test;

import java.io.*;

/**
 * @Author: liujinjian
 * @Date: 2020/5/27 9:35
 */
public class T {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        QueryRequest queryRequest = new QueryRequest();
//        queryRequest.setBizId("1001");
//        queryRequest.setUserName("liujinjian");
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("1.txt"));
//        objectOutputStream.writeObject(queryRequest);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("1.txt"));
        QueryRequest www = (QueryRequest) objectInputStream.readObject();
        System.out.println(www);

    }
}
