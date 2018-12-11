package com.ccue.datacenter.core.server.http.request;

import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HttpIntercepterManager implements Iterable<AbstractHttpIntercepter> {

    private static final Logger logger = LoggerFactory.getLogger(HttpIntercepterManager.class);
    private static int DEFAULT_INETERCEPTER_MAX = Short.MAX_VALUE;
    private AbstractHttpIntercepter head;
    private AbstractHttpIntercepter tail;
    private ConcurrentMap<String, AbstractHttpIntercepter> cacheMap = new ConcurrentHashMap<>();

    private HttpIntercepterManager() {
        this.tail = new HeadHttpIntercepter(this);
        this.head = new TailHttpIntercepter(this);
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
    }

    private static class Holder {
        private static HttpIntercepterManager manager = new HttpIntercepterManager();
        static {
            logger.info("HttpIntercepterManager Singleton was Created");
        }
    }

    public HttpIntercepterManager getInstance() {
        return Holder.manager;
    }

    private boolean registCheck(AbstractHttpIntercepter intercepter) {
        if (intercepter != null && cacheMap.size() < DEFAULT_INETERCEPTER_MAX) {
            cacheMap.putIfAbsent(intercepter.getIntercepterName(), intercepter);
            return true;
        }
        return false;
    }


    /**
     * 默认添加至最后
     * @param intercepter
     */
    public void regist(AbstractHttpIntercepter intercepter) {
        if (registCheck(intercepter)) {
            AbstractHttpIntercepter theLast = tail.prev();
            theLast.setNext(intercepter);
            intercepter.setPrev(theLast);
            intercepter.setNext(tail);
            tail.setPrev(intercepter);
        } else {
            logger.error("Intercepter 拦截器链已超过最大长度");
        }
    }

    public void addFirst(AbstractHttpIntercepter intercepter) {
        if (registCheck(intercepter)) {
            AbstractHttpIntercepter oldFirst = head.next();
            head.setNext(intercepter);
            intercepter.setPrev(head);
            intercepter.setNext(oldFirst);
            oldFirst.setPrev(intercepter);
            cacheMap.putIfAbsent(intercepter.getIntercepterName(), intercepter);
        } else {
            logger.error("Intercepter 拦截器链已超过最大长度");
        }
    }

    /**
     * 不要加太多interceper，性能会变差
     * @param request
     * @return
     */
    public boolean intercept(FullHttpRequest request) {
        boolean isAccept = true;
        AbstractHttpIntercepter intercepter = head;
        while (isAccept && !(intercepter instanceof TailHttpIntercepter)) {
            isAccept = intercepter.accept(request);
            intercepter = intercepter.next();
        }
        return isAccept;
    }


    @Override
    public Iterator<AbstractHttpIntercepter> iterator() {
        return this.cacheMap.values().iterator();
    }


    private class HeadHttpIntercepter extends AbstractHttpIntercepter {

        public HeadHttpIntercepter(HttpIntercepterManager holder) {
            super(holder);
        }

        @Override
        public boolean accept(FullHttpRequest request) {
            return true;
        }

    }

    private class TailHttpIntercepter extends AbstractHttpIntercepter{

        public TailHttpIntercepter(HttpIntercepterManager holder) {
            super(holder);
        }

        @Override
        public boolean accept(FullHttpRequest request) {
            return true;
        }

    }


}
