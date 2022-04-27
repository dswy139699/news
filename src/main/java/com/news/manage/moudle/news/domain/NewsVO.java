package com.news.manage.moudle.news.domain;

import com.news.manage.moudle.news.enums.StatusEnum;

public class NewsVO extends BaseVO{

    //栏目id
    private String tabId;
    //栏目名称
    private String tabName;
    //
    private String title;
    private String content;
    //作者id
    private String authorId;
    //
    private String authorName;



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

}
