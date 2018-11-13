package com.test.flume;

import java.util.regex.Pattern;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


//clog.sources.source_log3.interceptors.i3.type=com.thextrader.dmp.streaming.flume.BidInfoLogUrlFilter$Builder


public class FilterInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger(FilterInterceptor.class);
    @Override
    public void initialize() {

    }
    @Override
    public Event intercept(Event event) {
        String body = new String(event.getBody(), Charsets.UTF_8);
        String newBody;

        String[] aa = body.split("&");
        String uuid = aa[5];
        try {
            if (Pattern.compile("(?i)[a-z]").matcher(uuid).find()) {
                newBody = "12345667777777777777";
                event.setBody(newBody.getBytes());
            }
        }catch (Exception e){
            logger.warn(body,e);
            event=null;
        }

        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        List<Event> intercepted = Lists.newArrayListWithCapacity(events.size());
        for (Event event : events) {
            Event interceptedEvent = intercept(event);
            if (interceptedEvent != null) {
                intercepted.add(interceptedEvent);
            }
        }
        return intercepted;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{
        //使用Builder初始化Interceptor
        @Override
        public Interceptor build() {
            //add begin
            return new FilterInterceptor();
            //add end
        }

        @Override
        public void configure(Context context) {

        }
    }
}
