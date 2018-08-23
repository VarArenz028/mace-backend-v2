package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;

import java.io.Serializable;

/**
 * Created by Jabito on 21/12/2017.
 */
public class AttachmentObject implements Serializable{

    @JsonProperty("content")
    private byte[] content;

    @JsonProperty("headers")
    private HttpHeaders headers;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
}
