package com.basicauth.data;

import java.io.Serializable;

/**
 * Created by angulo on 11/14/2016.
 */
public class GeneratedId implements Serializable {

    private long id;

    private String name;

    private String prefix;

    private long length;

    private long no;

    public GeneratedId() {
    }

    public GeneratedId(long id, String name, String prefix, long length, long no) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.length = length;
        this.no = no;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }
}
