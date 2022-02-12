package com.serendipity.core.http;

/**
 * @Auther X .
 */
public enum  HttpContentType {

    Json("application/json"),
    Form("application/x-www-form-urlencoded"),
    File("multipart/form-data"),
    Xml("text/xml"),
    Html("text/html");

    final String text;

    public String getText() {
        return this.text;
    }

    private HttpContentType(String text) {
        this.text = text;
    }
}
