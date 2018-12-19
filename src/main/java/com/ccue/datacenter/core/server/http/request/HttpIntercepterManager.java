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

    public static HttpIntercepterManager getInstance() {
        return Holder.manager;
    }

    /**
     * 加入链式结构前的校验
     * 1. not null
     * 2. 不超过最大长度
     * 3. 非重复添加
     * @param intercepter
     * @return
     */
    private boolean registChecked(AbstractHttpIntercepter intercepter) {
        if (intercepter != null && cacheMap.size() < DEFAULT_INETERCEPTER_MAX && !cacheMap.containsKey(intercepter.getIntercepterName())) {
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
        if (registChecked(intercepter)) {
            addBefore(tail, intercepter);
        } else {
            logger.error("Intercepter 拦截器链已超过最大长度，未成功添加");
        }
    }

    public void addBefore(String intercepterNameBefore, AbstractHttpIntercepter current) throws Exception {
        AbstractHttpIntercepter before = cacheMap.get(intercepterNameBefore);
        addBefore(before, current);
    }

    public void addAfter(String intercepterNameAfter, AbstractHttpIntercepter current) throws Exception {
        AbstractHttpIntercepter after = cacheMap.get(intercepterNameAfter);
        addAfter(current, after);
    }

    /**
     * 添加至指定拦截器前
     * @param before
     * @param current
     */
    public void addBefore(AbstractHttpIntercepter before, AbstractHttpIntercepter current) {
        if (before != null && registChecked(current)) {
            AbstractHttpIntercepter prev = before.prev();
            prev.setNext(current);
            current.setPrev(prev);
            current.setNext(before);
            before.setPrev(current);
        }
    }

    /**
     * 添加至指定拦截器后
     * @param after
     * @param current
     */
    public void addAfter(AbstractHttpIntercepter after, AbstractHttpIntercepter current) {
        if (after != null && registChecked(current)) {
            AbstractHttpIntercepter next = after.next();
            after.setNext(current);
            current.setPrev(after);
            next.setPrev(current);
            current.setNext(next);
        }
    }


    /**
     * 添加至第一个
     * @param intercepter
     */
    public void addFirst(AbstractHttpIntercepter intercepter) {
        if (registChecked(intercepter)) {
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


    public void remove(String intercepterName) {
        AbstractHttpIntercepter intercepter = cacheMap.get(intercepterName);
        if (intercepter != null) {
            AbstractHttpIntercepter prev = intercepter.prev();
            AbstractHttpIntercepter next = intercepter.next();
            addAfter(prev, next);
        }
    }

    /**
     * 不要加太多interceper，性能会变差
     * @param request
     * @return
     */
    public boolean accept(FullHttpRequest request) {
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
