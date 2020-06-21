package com.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author annyu
 * @description flume定义切面
 * @date 2020/5/15
 **/
public class LogETLInterceptor implements Interceptor {

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        String log = new String(body, StandardCharsets.UTF_8);
        if(log.contains("start")){
            if(LogUntil.validateStart(log)){
                return event;
            }
        }else{
            if(LogUntil.validateEvent(log)){
                return event;
            }
        }
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        List<Event> interceptor = new ArrayList<>();

        for (Event event:events) {
            Event intercept = intercept(event);
            if(intercept!=null){
                interceptor.add(intercept);
            }

        }

        return interceptor;
    }

    @Override
    public void close() {

    }
    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new LogETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

}
