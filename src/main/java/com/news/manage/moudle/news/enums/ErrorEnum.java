package com.news.manage.moudle.news.enums;

public enum ErrorEnum {
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),
    USER_EXIST(2,"账号已存在"),
    FILE_SAVE_FAILED(3, "文件保存失败");

    private int code;
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsgByCode(int code) {
        for (ErrorEnum errorEnum : ErrorEnum.values()) {
            if (code == errorEnum.getCode()) return errorEnum.getMsg();
        }
        return "";
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
