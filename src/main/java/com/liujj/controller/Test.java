package com.liujj.controller;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liujinjian
 * @Date: 2020/7/29 12:48
 */
public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //event_type=COUPON.SEND&biz_event_type=EVENT_TYPE_BUSICOUPON_SEND&coupon_code=901438530212121&stock_id=1296010000000012&send_time=2019-12-17T10%3A35%3A53%2B08%3A00&openid=oCtYUww7W6l3qL9mPp7GPmx0kbJM&send_channel=BUSICOUPON_SEND_CHANNEL_MINIAPP&send_merchant=%22qweqe%22

        String jsonStr = "event_type=COUPON.SEND&biz_event_type=EVENT_TYPE_BUSICOUPON_SEND&coupon_code=901438530212121&stock_id=1296010000000012&send_time=2019-12-17T10%3A35%3A53%2B08%3A00&openid=oCtYUww7W6l3qL9mPp7GPmx0kbJM&send_channel=BUSICOUPON_SEND_CHANNEL_MINIAPP&send_merchant=%22qweqe%22";



        jsonStr = URLDecoder.decode(jsonStr,"UTF-8");
        String[] strs = jsonStr.split("&");
        Map<String, String> m = new HashMap<String, String>();
        for(String s:strs){
            String[] ms = s.split("=");
            m.put(ms[0], ms[1]);
        }
        System.out.println(m.get("factoryID"));

        System.out.println(m);


    }
}
