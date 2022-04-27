package com.news.manage.moudle.news.domain;

import com.news.manage.moudle.news.enums.StatusEnum;

//新闻动态查询入参模型
public class QueryModel extends BaseVO{
    //栏目id
    private String tabId;
    //栏目名称
    private String tabName;
    //栏目描述
    private String description;

    //作者id
    private String authorId;
    //作者姓名
    private String authorName;
    //新闻标题
    private String title;
    //新闻内容
    private String content;

    private StatusEnum statusEnum = StatusEnum.ACTIVE;

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
