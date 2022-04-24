package com.news.manage.moudle.news.enums;

public enum StatusEnum {
    ACTIVE("ACTIVE","正常"),
    DELETED("DELETED","删除"),
    HIDE("HIDE","隐藏")
    ;

    private String code;
    private String msg;

    StatusEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getMsgByCode(String code){
        for(StatusEnum statusEnum : StatusEnum.values()){
            if(code.equals(statusEnum.getCode())) return statusEnum.getMsg();
        }
        return "";
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

}

