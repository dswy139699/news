package com.news.manage.moudle.news.repo.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "NEWS_COMMENT")
public class CommentEntity extends BaseEntity{
    @Column(name = "TAB_ID")
    private String tabId;
    @Column(name = "NEWS_ID")
    private String newsId;
    @Column(name = "LINKED_AUTHOR_ID")
    private String linkedAuthorId;
    @Column(name = "LINKED_AUTHOR_NAME")
    private String linkedAuthorName;
    @Column(name = "LINKED_COMMENT_ID")
    private String linkedCommentId;
    @Column(name = "COMMENT_BODY")
    private String commentBody;
    @Column(name = "AUTHOR_ID")
    private String authorId;
    @Column(name = "AUTHOR_NAME")
    private String authorName;

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

}
