package com.news.manage.moudle.news.repo.dao;


import com.news.manage.moudle.news.enums.StatusEnum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "STATUS_ENUM")
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @PrePersist
    public void generateTime(){
        if(this.createTime == null){
            this.createTime = new Date();
        }
    }

    @PreUpdate
    public void generateUpdateTime(){
        if(this.updateTime == null){
            this.updateTime = new Date();
        }
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    public Date getCreateTime(){
        return this.createTime;
    }


    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    public Date getUpdateTime(){
        return this.updateTime;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
