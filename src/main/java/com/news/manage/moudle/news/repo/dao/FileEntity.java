package com.news.manage.moudle.news.repo.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "NEWS_FILE")
public class FileEntity extends BaseEntity {
    @Column(name = "ORIGIN_NAME")
    private String originName;
    @Column(name = "FILE_TYPE")
    private String fileType;
    @Column(name = "CURRENT_NAME")
    private String currentName;
    @Column(name = "FILE_PATH")
    private String filePath;

    public FileEntity(String originName, String fileType, String currentName, String filePath){
        this.originName = originName;
        this.fileType = fileType;
        this.currentName = currentName;
        this.filePath = filePath;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
