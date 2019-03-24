package com.ccue.datacenter.core.server.http.mvc.dispatch;

import com.ccue.datacenter.core.server.http.HttpServerContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.rtsp.RtspHeaderNames.CONTENT_LENGTH;

/**
 * MVC入口
 * 请求调度器，这是控制器
 * 适配自己的MVC
 * 必须将server和mvc解藕
 */
public class NettyHttpDispatcher extends AbstractHttpDispatcher {

    private static final String DEFAULT_Protocal = "HTTP";

    private static final String HTTP_URL_PARAMS_SEP = "?";

    private static final String HTTP_PARAM_SEP = "&";

    private static final String HTTP_PARAM_PAIR_SEP = "=";



    /**
     * 拦截时返回默认消息
     */
    private static final String DEFAULT_NOT_SUPPORT_MSG = "{\"code\":200, \"msg\":\"method not was supported!\"}";

    public static FullHttpResponse DEFAULT_NOT_SUPPORT_RESPONSE = new DefaultFullHttpResponse(
            HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
            Unpooled.wrappedBuffer(DEFAULT_NOT_SUPPORT_MSG.getBytes()));
    static {
        DEFAULT_NOT_SUPPORT_RESPONSE.headers().set(CONTENT_TYPE, "application/json");
        DEFAULT_NOT_SUPPORT_RESPONSE.headers().setInt(CONTENT_LENGTH, DEFAULT_NOT_SUPPORT_RESPONSE.content().readableBytes());
    }


    @Override
    public FullHttpResponse dispatch(FullHttpRequest request) {
        FullHttpResponse response = DEFAULT_NOT_SUPPORT_RESPONSE;
        HttpMethod method = request.method();
        //得到参数封装并转发
        if (HttpMethod.GET.equals(method)) {
            response = doGet(request);
            return response;
        } else if (HttpMethod.POST.equals(request.method())) {
            response = doPost(request);
        } else if (HttpMethod.PUT.equals(request.method())){
            // not supported
            response = doPut(request);
        } else if (HttpMethod.HEAD.equals(request.method())) {
            // not supported
            response = doHead(request);
        }
        return response;
    }

    private void initRouter(HttpServerContext httpServerContext) {

    }

    private void initMappingHandler(HttpServerContext httpServerContext) {

    }

    /**
     * 处理Get请求
     * @param request
     * @return
     */
    private FullHttpResponse doGet(FullHttpRequest request) {
        HttpVersion version = request.protocolVersion();
        HttpHeaders headers = request.headers();

        String url = request.uri();
        Map<String, String> map = null;
        if (url.indexOf(HTTP_URL_PARAMS_SEP) != -1) {
            int index = url.indexOf(HTTP_URL_PARAMS_SEP);
            String reqParams = url.substring(index + 1);
            map = parseParameters(reqParams);
        }
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set("content-Type","text/html;charset=UTF-8");
        StringBuilder sb = new StringBuilder();
        response.setStatus(null);
        sb.append("abc");
        ByteBuf responseBuf = Unpooled.copiedBuffer(sb, CharsetUtil.UTF_8);
        try {
            response.content().writeBytes(responseBuf);
        } finally {
            responseBuf.release();
        }
        return response;
    }

    /**
     * 解析Http中GET方法中的路径参数
     * @param paramStrs
     * @return
     */
    private static Map<String, String> parseParameters(String paramStrs) {
        Map<String, String> map = new HashMap<>();
        if (paramStrs != null) {
            String[] pairs = paramStrs.split(HTTP_PARAM_SEP);
            for (String str : pairs) {
                String[] pair = str.split(HTTP_PARAM_PAIR_SEP);
                if (pair != null && pair.length == 2) {
                    map.put(pair[0], pair[1]);
                }
            }
        }
        return map;
    }

    /**
     * 处理Post请求
     * @param request
     * @return
     */
    private FullHttpResponse doPost(FullHttpRequest request) {
        return null;
    }

    /**
     * 处理Put请求
     * @param request
     * @return
     */
    private FullHttpResponse doPut(FullHttpRequest request) {
        return null;
    }

    /**
     * 处理Head请求
     * @param request
     * @return
     */
    private FullHttpResponse doHead(FullHttpRequest request) {
        return null;
    }


}
