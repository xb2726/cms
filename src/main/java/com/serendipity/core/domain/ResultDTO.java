package com.serendipity.core.domain;

import com.serendipity.core.enums.ResultCode;

import java.io.Serializable;

/**
 * @Auther X .
 */
public class ResultDTO<T> implements Serializable {

    private Integer code;
    private Boolean success;
    private String message;
    private T data;
    private Long timestamp;

    private ResultDTO(T data) {
        this.success = true;
        this.code = 200;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    private ResultDTO(String message, int code) {
        this.success = code == 200;
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public ResultDTO() {

    }

    private static <T> ResultDTO<T> result(ResultCode resultCode, String message) {
        return new ResultDTO(message, resultCode.getCode());
    }

    private static <T> ResultDTO<T> result(ResultCode resultCode) {
        return new ResultDTO(resultCode.getMessage(), resultCode.getCode());
    }

    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO(data);
    }

    public static <T> ResultDTO<T> success() {
        return result(ResultCode.SUCCESS);
    }

    public static <T> ResultDTO<T> fail(int code, String message) {
        return new ResultDTO(message, code);
    }

    public static <T> ResultDTO<T> fail(ResultCode resultCode) {
        return result(resultCode);
    }

    public static <T> ResultDTO<T> fail(ResultCode resultCode, String message) {
        return result(resultCode, message);
    }

    public Integer getCode() {
        return this.code;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        return "ResultDTO(code=" + this.getCode() + ", success=" + this.getSuccess() + ", message=" + this.getMessage() + ", data=" + this.getData() + ", timestamp=" + this.getTimestamp() + ")";
    }
}
