package com.liujj.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liujinjian
 * @Date: 2020/7/29 9:48
 */
@Data
public class Student implements Serializable {

    private String username;

    private String pass_word;


}
