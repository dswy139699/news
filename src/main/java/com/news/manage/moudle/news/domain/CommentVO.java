package com.news.manage.moudle.news.domain;

public class CommentVO extends BaseVO{


    private String newsId;
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
}
