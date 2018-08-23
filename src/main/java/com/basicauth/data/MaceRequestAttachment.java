package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Lawrence on 6/28/2017.
 */

@Entity
@Table(name = "MACE_REQUEST_ATTACHMENTS")
public class MaceRequestAttachment implements Serializable{



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "id")
    private int id;

    @JsonProperty("requestId")
    @Column(name = "request_id")
    private int requestId;



    @JsonProperty("fileId")
    @Column(name = "file_id")
    private String fileId;



    @JsonProperty("contentType")
    @Column(name = "content_type")
    private String contentType;



    @JsonProperty("originalFileName")
    @Column(name = "original_file_name")
    private String originalFileName;



    @JsonProperty("fileSuffix")
    @Column(name = "file_suffix")
    private String fileSuffix;



    @JsonProperty("fileNameNoSuffix")
    @Column(name = "file_name_no_suffix")
    private String fileNameNoSuffix;



    @JsonProperty("content")
    @Column(name = "content")
    private byte[] content;



    @JsonProperty("requestCode")
    @Column(name = "request_code")
    private String requestCode;

    @JsonProperty("transactionId")
    @Column(name = "transaction_id")
    private String transactionId;

    public MaceRequestAttachment() { }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getRequestId() { return requestId; }

    public void setRequestId(int request_id) { this.requestId = request_id; }

    public String getFileId() { return fileId; }

    public void setFileId(String fileId) { this.fileId = fileId; }

    public String getContentType() { return contentType; }

    public void setContentType(String contentType) { this.contentType = contentType; }

    public String getOriginalFileName() { return originalFileName; }

    public void setOriginalFileName(String originalFileName) { this.originalFileName = originalFileName; }

    public String getFileSuffix() { return fileSuffix; }

    public void setFileSuffix(String fileSuffix) { this.fileSuffix = fileSuffix; }

    public String getFileNameNoSuffix() { return fileNameNoSuffix; }

    public void setFileNameNoSuffix(String fileNameNoSuffix) { this.fileNameNoSuffix = fileNameNoSuffix; }

    public byte[] getContent() { return content; }

    public void setContent(byte[] content) { this.content = content; }

    public String getRequestCode() { return requestCode; }

    public void setRequestCode(String request_code) { this.requestCode = request_code; }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
