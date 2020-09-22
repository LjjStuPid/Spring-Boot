package com.liujj.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liujinjian
 * @Date: 2020/6/11 12:37
 */
@Data
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = -3991278263387460017L;


    private String bizId;
}
