package com.liujj.rocketmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liujinjian
 * @Date: 2020/6/18 19:31
 */
@RestController
public class TestController {

    @Autowired
    private ProducerService producer;

    @RequestMapping("/push")
    public String pushMsg(String msg) {
        return producer.send("TopicTest", "push", msg);
    }

    @RequestMapping("/ww")
    public String ww(String msg) {
        System.out.println("fdsafdsafds");
        return producer.send("TopicTest", "push", msg);
    }


}
