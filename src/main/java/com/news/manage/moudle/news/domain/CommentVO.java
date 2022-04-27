package com.news.manage.moudle.news.domain;

import javax.persistence.Column;

public class CommentVO extends BaseVO{

    private String tabId;
    private String tabName;
    private String newsId;
    private String title;
    private String linkedCommentId;
    private String linkedAuthorId;
    private String linkedAuthorName;
    private String commentBody;
    private String authorId;
    private String authorName;

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getLinkedCommentId() {
        return linkedCommentId;
    }

    public void setLinkedCommentId(String linkedCommentId) {
        this.linkedCommentId = linkedCommentId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
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

    public String getLinkedAuthorId() {
        return linkedAuthorId;
    }

    public void setLinkedAuthorId(String linkedAuthorId) {
        this.linkedAuthorId = linkedAuthorId;
    }

    public String getLinkedAuthorName() {
        return linkedAuthorName;
    }

    public void setLinkedAuthorName(String linkedAuthorName) {
        this.linkedAuthorName = linkedAuthorName;
    }

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
}
