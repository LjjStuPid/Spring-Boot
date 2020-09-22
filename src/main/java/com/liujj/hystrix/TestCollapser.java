package com.liujj.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.HystrixRequestLog;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: liujinjian
 * @Date: 2020/6/1 17:54
 */
public class TestCollapser {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        Future<String> f1 = new HystrixCollapserRequest(1).queue();
        Future<String> f2 = new HystrixCollapserRequest(2).queue();
        Future<String> f3 = new HystrixCollapserRequest(3).queue();
        Future<String> f4 = new HystrixCollapserRequest(4).queue();

        System.out.println("collasper: 1 " + f1.get());
        System.out.println("collasper: 2 " + f2.get());
        System.out.println("collasper: 3 " + f3.get());
        System.out.println("collasper: 4 " + f4.get());

        //当前的请求数 自动合并请求 总共发送一次请求
        HystrixCommand<?> command = HystrixRequestLog.getCurrentRequest().getExecutedCommands().toArray(new HystrixCommand<?>[1])[0];

        System.out.println("GetValueForKey " + command.getCommandKey().name());

        System.out.println(command.getExecutionEvents().contains(HystrixEventType.COLLAPSED));


        System.out.println(command.getExecutionEvents().contains(HystrixEventType.SUCCESS));

        context.shutdown();
    }


}
