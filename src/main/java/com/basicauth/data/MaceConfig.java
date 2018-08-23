package com.basicauth.data;

import java.io.Serializable;

/**
 * Created by Rall Llobrera on 02/06/2017.
 */
public class MaceConfig implements Serializable {

    private int id;

    private String key1;

    private String key2;

    private String key3;

    private String valueType;

    private String valueContents;

    public MaceConfig() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getKey3() {
        return key3;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValueContents() {
        return valueContents;
    }

    public void setValueContents(String valueContents) {
        this.valueContents = valueContents;
    }
}
