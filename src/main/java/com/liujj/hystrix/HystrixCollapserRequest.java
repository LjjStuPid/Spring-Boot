package com.liujj.hystrix;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: liujinjian
 * @Date: 2020/6/1 17:47
 */
public class HystrixCollapserRequest extends HystrixCollapser<List<String>, String, Integer> {

    private final Integer key;

    public HystrixCollapserRequest(Integer key) {
        this.key = key;
    }

    @Override
    public Integer getRequestArgument() {
        return key;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Integer>> collection) {
        return new BatchCommand(collection);
    }

    @Override
    protected void mapResponseToRequests(List<String> stringList, Collection<CollapsedRequest<String, Integer>> collection) {
        int count = 0;
        for (CollapsedRequest<String, Integer> request : collection) {
            request.setResponse(stringList.get(count++));
        }
    }

    private static final class BatchCommand extends HystrixCommand<List<String>> {

        private final Collection<CollapsedRequest<String, Integer>> requests;

        private BatchCommand(Collection<CollapsedRequest<String, Integer>> requests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey")));
            this.requests = requests;
        }

        @Override
        protected List<String> run() throws Exception {
            ArrayList<String> response = new ArrayList<>();
            for (CollapsedRequest<String, Integer> request : requests) {
                // 批量接受到的每个参数的响应
                response.add("collapser: " + request.getArgument());
            }
            return response;
        }
    }
}
