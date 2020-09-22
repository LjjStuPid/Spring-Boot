package com.liujj.rocketmq;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: liujinjian
 * @Date: 2020/6/18 19:22
 */
@Component
public class Consumer {

    @Value("${apache.rocketmq.consumer.PushConsumer}")
    private String consumerGroup;
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    public void defaultMQPushConsumer(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        try{
            consumer.subscribe("TopicTest","push");

            // 如果是第一次启动，从队列头开始消费，否则，从上次消费的位置开始
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            consumer.registerMessageListener(new MessageListenerConcurrently(){
                @SneakyThrows
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    for (MessageExt messageExt : list) {
                        String messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                        System.out.println("[Consumer] msgID(" + messageExt.getMsgId() + ") msgBody : " + messageBody);
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            System.out.println("[Consumer 已启动]");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
