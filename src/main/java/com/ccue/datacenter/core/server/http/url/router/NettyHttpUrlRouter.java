package com.ccue.datacenter.core.server.http.url.router;

import com.ccue.datacenter.core.server.http.HttpServerContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

public class NettyHttpUrlRouter implements UrlRouter<FullHttpRequest, FullHttpResponse>{

    private static final String DEFAULT_Protocal = "HTTP";

    private static final String HTTP_URL_PARAMS_SEP = "?";

    private static final String HTTP_PARAM_SEP = "&";

    private static final String HTTP_PARAM_PAIR_SEP = "=";

    public FullHttpResponse route(FullHttpRequest request) {

        HttpMethod method = request.method();
        String url = request.uri();
        ByteBuf content = request.content();
        byte[] bts = new byte[content.readableBytes()];
        content.readBytes(bts);
        String result = null;

        //得到参数封装并转发
        if (HttpMethod.GET.equals(method)) {
            String paramStrs = null;
            String relativePath = url;
            if (url.indexOf(HTTP_URL_PARAMS_SEP) != -1) {
                paramStrs = url.substring(url.indexOf(HTTP_URL_PARAMS_SEP) + 1);
                relativePath = url.substring(0, url.indexOf(HTTP_URL_PARAMS_SEP));
                Map<String, String> map = parseParameters(paramStrs);

                // 根据路径、method寻找controoler
                // httprequest的对象
                HttpServerContext context = this.getServerContext();

            }
        } else if (HttpMethod.POST.equals(request.method())) {
            System.out.println("post:" + url);
            result = "post method and paramters is "+ new String(bts);
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


    private void doGet() {

    }


    private void doPost() {

    }

}
