package com.serendipity.core.http;

import cn.hutool.core.util.XmlUtil;
import com.serendipity.core.utils.JsonUtils;
import com.serendipity.utils.CommonUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther X .
 */
public class HttpResponse {
    private Integer code = 404;
    private String charset = "UTF-8";
    private byte[] body;

    public HttpResponse() {
    }

    public InputStream readStream() {
        return this.body == null ? null : new ByteArrayInputStream(this.body);
    }

    public boolean isSuccessCode() {
        return this.code != 200;
    }

    public String readBody() {
        return this.readBody("");
    }

    public String readBody(String charset) {
        if (this.body == null) {
            return null;
        } else {
            try {
                if (CommonUtils.isEmpty(charset)) {
                    charset = this.charset;
                }

                return new String(this.body, charset);
            } catch (IOException var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public <T> T readBody(Class<T> clazz) {
        if (this.isSuccessCode()) {
            return null;
        } else {
            String body = this.readBody();
            return JsonUtils.json(body, clazz);
        }
    }

    public <T> T readBodyFromXml() {
        if (this.isSuccessCode()) {
            return null;
        } else {
            String body = this.readBody();
            return XmlUtil.readObjectFromXml(body);
        }
    }

    public Integer getCode() {
        return this.code;
    }

    public String getCharset() {
        return this.charset;
    }

    public byte[] getBody() {
        return this.body;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
