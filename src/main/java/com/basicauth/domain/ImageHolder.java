package com.basicauth.domain;

import java.io.Serializable;

/**
 * Created by angulo on 10/25/2016.
 */
public class ImageHolder implements Serializable{

    int id;
    String memCode;
    String contentType;
    String originalFileName;
    String fileSuffix;
    String fileNameNoSuffix;
    String fileId;
    byte[] content;

    public ImageHolder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileNameNoSuffix() {
        return fileNameNoSuffix;
    }

    public void setFileNameNoSuffix(String fileNameNoSuffix) {
        this.fileNameNoSuffix = fileNameNoSuffix;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
