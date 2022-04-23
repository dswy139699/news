package com.news.manage.moudle.news.repo.dao;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class TabEntity extends BaseEntity{
    @Column(name = "TAB_NAME")
    private String tabName;
    @Column(name = "DESCRIBE")
    private String describe;
    @Column(name = "CREATOR_ID")
    private String creatorId;
}
