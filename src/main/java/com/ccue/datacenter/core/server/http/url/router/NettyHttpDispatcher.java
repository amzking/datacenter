package com.ccue.datacenter.core.server.http.url.router;

import com.ccue.datacenter.core.server.http.HttpServerContext;
import com.ccue.datacenter.core.server.http.request.HttpRequest;
import com.ccue.datacenter.core.server.http.response.HttpResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求调度器，路由至自己实现的mvc
 */
public class NettyHttpDispatcher implements IHttpDispatcher {

    private static final String DEFAULT_Protocal = "HTTP";

    private static final String HTTP_URL_PARAMS_SEP = "?";

    private static final String HTTP_PARAM_SEP = "&";

    private static final String HTTP_PARAM_PAIR_SEP = "=";

    private static HttpGetRequestProcessor GetProcessor = null;

    /**
     * 弱引用，存在内存泄露的可能性？
     */
    private static final ThreadLocal<HttpGetRequestProcessor> GetProcessorThreadLocal = new ThreadLocal<HttpGetRequestProcessor>(){
        @Override
        protected HttpGetRequestProcessor initialValue() {
            return GetProcessor;
        }
    };

    @Override
    public FullHttpResponse dispatch(FullHttpRequest request) {

        HttpMethod method = request.method();


        //得到参数封装并转发
        if (HttpMethod.GET.equals(method)) {

        } else if (HttpMethod.POST.equals(request.method())) {

        } else {
            // not support
        }

        HttpVersion version = request.protocolVersion();
        HttpHeaders headers = request.headers();

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);


        response.headers().set("content-Type","text/html;charset=UTF-8");
        StringBuilder sb = new StringBuilder();

        sb.append("abc");

        ByteBuf responseBuf = Unpooled.copiedBuffer(sb, CharsetUtil.UTF_8);
        response.content().writeBytes(responseBuf);
        responseBuf.release();
        return response;

    }

    @Override
    public HttpServerContext getServerContext() {
        return null;
    }


    private static Map<String, String> parseParameters(String paramStrs) {
        String[] pairs = paramStrs.split(HTTP_PARAM_SEP);
        Map<String, String> map = new HashMap<>();
        for (String str : pairs) {
            String[] pair = str.split(HTTP_PARAM_PAIR_SEP);
            if (pair != null && pair.length == 2) {
                map.put(pair[0], pair[1]);
            }
        }
        return map;
    }


    //getprocessor static
    public static HttpRequestProcessor getRequestProcessor (){
        /*HttpRequestProcessor session =  threadLocal.get();
        //判断Session是否为空，如果为空，将创建一个session，并设置到本地线程变量中
        try {
        } finally {
            threadLocal.remove();
        }*/
        return null;
    }

    private abstract class HttpRequestProcessor {


        public abstract FullHttpResponse process(FullHttpRequest request);
    }


    public class HttpGetRequestProcessor extends HttpRequestProcessor {

        @Override
        public FullHttpResponse process(FullHttpRequest request) {
            String url = request.uri();
            ByteBuf content = request.content();
            byte[] bts = new byte[content.readableBytes()];
            content.readBytes(bts);
            String result = null;
            String paramStrs = null;
            String relativePath = url;
            if (url.indexOf(HTTP_URL_PARAMS_SEP) != -1) {
                paramStrs = url.substring(url.indexOf(HTTP_URL_PARAMS_SEP) + 1);
                relativePath = url.substring(0, url.indexOf(HTTP_URL_PARAMS_SEP));
                Map<String, String> map = parseParameters(paramStrs);

                // 根据路径、method寻找controoler
                // httprequest的对象
                HttpServerContext context = NettyHttpDispatcher.this.getServerContext();

            }
            return null;
        }
    }

    private class HttpPostRequestProcessor extends HttpRequestProcessor {

        @Override
        public FullHttpResponse process(FullHttpRequest request) {
            return null;
        }
    }

}
