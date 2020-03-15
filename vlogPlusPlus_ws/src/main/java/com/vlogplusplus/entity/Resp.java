package com.vlogplusplus.entity;

public class Resp<E> {

    private String code;

    private String message;

    private E body;

    public Resp(String code, String message, E body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getBody() {
        return body;
    }

    public void setBody(E body) {
        this.body = body;
    }

    public static <E> Resp<E> customFail(String code, String message) {
        return new Resp(code, message, (Object)null);
    }

    public static <E> Resp<E> bad(String message) {
        return new Resp("400", message, (Object)null);
    }

    public static <E> Resp<E> error(String message) {
        return new Resp("500", message, (Object)null);
    }

    public static <E> Resp<E> success(E body) {
        return new Resp("200", "", body);
    }
}
