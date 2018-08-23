package com.basicauth.data;

import java.io.Serializable;

/**
 * Created by angulo on 11/16/2016.
 */
public class Province implements Serializable {

    private String provinceCode;
    private String provinceName;
    private String regionCode;

    public Province() {
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
}
