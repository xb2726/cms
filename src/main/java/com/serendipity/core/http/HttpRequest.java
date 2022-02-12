package com.serendipity.core.http;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.XmlUtil;
import com.serendipity.core.utils.JsonUtils;
import com.serendipity.core.utils.MapUtils;
import com.serendipity.utils.CommonUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther X .
 */
public class HttpRequest {

    private String url;
    private HttpMethod method;
    private HttpContentType contentType;
    private String charset;
    private Object params;
    private Object data;
    private Integer connectionTimeout;
    private Integer readTimeout;
    private Map<String, String> header;
    private Map<String, byte[]> files;

    public HttpRequest(String url) {
        this(url, HttpMethod.GET, (Object)null);
    }

    public HttpRequest(String url, HttpMethod method) {
        this(url, method, (Object)null);
    }

    public HttpRequest(String url, HttpMethod method, Object data) {
        this.url = url;
        this.method = method;
        this.data = data;
        this.connectionTimeout = 15000;
        this.readTimeout = 15000;
        this.charset = "UTF-8";
        this.contentType = HttpContentType.Json;
        this.header = new HashMap();
        this.files = new HashMap();
        this.header.put("accept", "*/*");
        this.header.put("connection", "Keep-Alive");
        this.header.put("User-Agent", "payment-sdk/v1.0.0");
    }

    public String getUrl() {
        if (this.params == null) {
            return this.url;
        } else {
            Map<String, Object> map = MapUtils.map(this.params);
            String params = MapUtils.toUrl(map, this.charset);
            if (CommonUtils.isEmpty(params)) {
                return this.url;
            } else if (CommonUtils.isEmpty(this.url)) {
                return params;
            } else {
                String split = "?";
                if (this.url.indexOf(split) > 0) {
                    split = "&";
                }

                return this.url.concat(split).concat(params);
            }
        }
    }

    public String getContentType(String boundary) {
        return String.format("%s; boundary=%s", this.contentType.getText(), boundary);
    }

    public byte[] getContent() {
        if (this.data == null) {
            return null;
        } else {
            String content;
            switch(this.contentType) {
                case Xml:
                    content = XmlUtil.mapToXmlStr(BeanUtil.beanToMap(this.data));
                    break;
                case Json:
                    content = JsonUtils.toJson(this.data);
                    break;
                case Form:
                    content = MapUtils.toUrl(BeanUtil.beanToMap(this.data), this.charset);
                    break;
                default:
                    return null;
            }

            if (content != null) {
                try {
                    return content.getBytes(this.charset);
                } catch (Exception var3) {
                    var3.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    public void addFile(String path) {
        byte[] buffer = FileUtil.readBytes(path);
        String fileName = FileUtil.getName(path);
        this.addFile(fileName, buffer);
    }

    public void addFile(String fileName, byte[] fileData) {
        if (this.contentType != HttpContentType.File) {
            this.contentType = HttpContentType.File;
        }

        this.files.put(fileName, fileData);
    }

    public boolean hasFile() {
        return this.files != null && this.files.size() > 0;
    }

    public void writeFiles(OutputStream os, String boundary) {
        if (this.hasFile()) {
            byte[] endBuffer = String.format("\r\n--%s--\r\n", boundary).getBytes();
            String template = "\r\n--%s\r\nContent-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\nContent-Type:%s\r\n\r\n";

            try {
                Iterator var5 = this.files.keySet().iterator();

                while(var5.hasNext()) {
                    String key = (String)var5.next();

                    try {
                        String contentType = FileUtil.getMimeType(key);
                        if (!CommonUtils.isEmpty(contentType)) {
                            String header = String.format(template, boundary, key, key, contentType);
                            System.out.println(header);
                            os.write(header.getBytes());
                            os.write((byte[])this.files.get(key));
                        }
                    } catch (IOException var9) {
                        var9.printStackTrace();
                    }
                }

                os.write(endBuffer);
            } catch (IOException var10) {
                var10.printStackTrace();
            }

        }
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public HttpContentType getContentType() {
        return this.contentType;
    }

    public String getCharset() {
        return this.charset;
    }

    public Object getParams() {
        return this.params;
    }

    public Object getData() {
        return this.data;
    }

    public Integer getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public Integer getReadTimeout() {
        return this.readTimeout;
    }

    public Map<String, String> getHeader() {
        return this.header;
    }

    public Map<String, byte[]> getFiles() {
        return this.files;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public void setContentType(HttpContentType contentType) {
        this.contentType = contentType;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public void setFiles(Map<String, byte[]> files) {
        this.files = files;
    }
}
