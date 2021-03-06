package com.news.manage.moudle.news.repo.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "NEWS_TAB")
public class TabEntity extends BaseEntity{
    @Column(name = "TAB_NAME")
    private String tabName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "AUTHOR_ID")
    private String authorId;
    @Column(name = "AUTHOR_NAME")
    private String authorName;

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
