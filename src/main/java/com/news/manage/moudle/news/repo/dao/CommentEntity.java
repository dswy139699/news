package com.news.manage.moudle.news.repo.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "NEWS_COMMENT")
public class CommentEntity extends BaseEntity{
    @Column(name = "NEWS_ID")
    private String newsId;
    @Column(name = "LINKED_COMMENT_ID")
    private String linkedCommentId;
    @Column(name = "COMMENT_BODY")
    private String commentBody;
    @Column(name = "COMMENTER_ID")
    private String commenterId;
    @Column(name = "COMMENTER_NAME")
    private String commenterName;

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

    public String getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(String commenterId) {
        this.commenterId = commenterId;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }
}
