package com.news.manage.moudle.news.domain;

import java.io.Serializable;

public class BaseVO implements Serializable {
    //新闻唯一id
    private String uuid;
    //创建时间，格式 yyyy-MM-dd hh:mm:ss
    private String createTime;
    //更新时间
    private String updateTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
