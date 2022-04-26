package com.news.manage.moudle.news.domain;

import javax.persistence.Column;

public class CommentVO extends BaseVO{


    private String newsId;
    private String linkedCommentId;
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

}
