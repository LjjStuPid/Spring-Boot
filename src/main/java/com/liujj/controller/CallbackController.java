package com.liujj.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liujinjian
 * @Date: 2020/7/29 9:43
 */
@RestController
public class CallbackController {

    @PostMapping("/finance/coupon/callback")
    public void test(CouponCallbackRequest request) {
        System.out.println(request);
    }


}
