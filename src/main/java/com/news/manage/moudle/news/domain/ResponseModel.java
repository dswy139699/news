package com.news.manage.moudle.news.domain;

public class ResponseModel<T> {
    private int code;
    private String msg;
    private T data;

    public ResponseModel(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

