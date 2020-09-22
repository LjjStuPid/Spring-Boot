package com.liujj.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.UnsupportedEncodingException;

/**
 * @Author: liujinjian
 * @Date: 2020/6/18 19:14
 */
@Service
public class ProducerService {


    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup;

    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;


    private DefaultMQProducer producer;

    public void initProducer() {
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setRetryTimesWhenSendFailed(3);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }


    public String send(String topic, String tags, String msg) {
        SendResult result = null;
        try {
            Message message = new Message(topic, tags, msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
            result = producer.send(message);
            System.out.println("[Producer] msgID(" + result.getMsgId() + ") " + result.getSendStatus());
        } catch (UnsupportedEncodingException | MQClientException | InterruptedException | RemotingException | MQBrokerException e) {
            e.printStackTrace();
        }
        return "{\"MsgId\":\"" + result.getMsgId() + "\"}";
    }

    @PreDestroy
    public void shutDownProducer() {
        if (producer != null) {
            producer.shutdown();
        }
    }

}
