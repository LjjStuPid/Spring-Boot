package com.liujj.test;

import lombok.Data;

/**
 * @Author: liujinjian
 * @Date: 2020/6/11 12:41
 */
@Data
public class QueryRequest extends BaseRequest {

    private static final long serialVersionUID = -5701747701573389538L;

    private String userName;

    private String password;

    private String sex;


}
