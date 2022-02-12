package com.binx.core.http;

import cn.hutool.core.util.IdUtil;
import com.binx.core.utils.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther X .
 */
public class HttpHelper {


    public static HttpResponse request(HttpRequest request) {
        HttpURLConnection connection = null;
        OutputStream os = null;
        HttpResponse response = new HttpResponse();

        HttpResponse var5;
        try {
            String url = request.getUrl();
            HttpMethod method = request.getMethod();
            System.out.println(String.format("Http Request:%s  %s", method, url));
            connection = (HttpURLConnection)(new URL(url)).openConnection();
            connection.setRequestMethod(method.toString());
            connection.setConnectTimeout(request.getConnectionTimeout());
            connection.setReadTimeout(request.getReadTimeout());
            Map<String, String> header = request.getHeader();
            Iterator var7 = header.keySet().iterator();

            while(var7.hasNext()) {
                String key = (String)var7.next();
                connection.setRequestProperty(key, (String)header.get(key));
            }

            String boundary = "";
            if (request.hasFile()) {
                boundary = "---------------------------".concat(IdUtil.fastSimpleUUID());
                connection.setRequestProperty("Content-Type", request.getContentType(boundary));
            } else {
                connection.setRequestProperty("Content-Type", request.getContentType().getText());
            }

            connection.setDoInput(true);
            if (method != HttpMethod.GET) {
                connection.setDoOutput(true);
                os = connection.getOutputStream();
                if (request.hasFile()) {
                    request.writeFiles(os, boundary);
                } else if (request.getData() != null) {
                    os.write(request.getContent());
                } else {
                    connection.setRequestProperty("Content-Length", "0");
                }
            }

            connection.connect();
            int code = connection.getResponseCode();
            response.setCode(code);
            InputStream body = connection.getInputStream();
            response.setBody(StreamUtils.input2byte(body));
            HttpResponse var10 = response;
            return var10;
        } catch (IOException var20) {
            var20.printStackTrace();
            var5 = response;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException var19) {
                    var19.printStackTrace();
                }
            }

            if (connection != null) {
                connection.disconnect();
            }

        }

        return var5;
    }

    public static HttpResponse get(String url) {
        HttpRequest request = new HttpRequest(url);
        return request(request);
    }

    public static HttpResponse get(String url, Object params) {
        HttpRequest request = new HttpRequest(url);
        request.setParams(params);
        return request(request);
    }

    public static HttpResponse post(String url, Object data) {
        HttpRequest request = new HttpRequest(url, HttpMethod.POST, data);
        return request(request);
    }

    public static HttpResponse post(String url, Object data, HttpContentType type) {
        HttpRequest request = new HttpRequest(url, HttpMethod.POST, data);
        request.setContentType(type);
        return request(request);
    }

    public static HttpResponse put(String url, Object data) {
        HttpRequest request = new HttpRequest(url, HttpMethod.PUT, data);
        return request(request);
    }

    public static HttpResponse put(String url, Object data, HttpContentType type) {
        HttpRequest request = new HttpRequest(url, HttpMethod.PUT, data);
        request.setContentType(type);
        return request(request);
    }

    public static HttpResponse delete(String url, Object params) {
        HttpRequest request = new HttpRequest(url, HttpMethod.DELETE);
        request.setParams(params);
        return request(request);
    }

    public static HttpResponse delete(String url, Object data, HttpContentType type) {
        HttpRequest request = new HttpRequest(url, HttpMethod.DELETE, data);
        request.setContentType(type);
        return request(request);
    }
}
