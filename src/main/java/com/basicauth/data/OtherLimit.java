package com.basicauth.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;

/**
 * Created by angulo on 11/23/2016.
 */
public class OtherLimit implements Serializable{

    double id;
    BigDecimal stdLimit;
    BigDecimal allowLimit;
    BigDecimal limitWhenPecEqualDdl;
    BigDecimal limitWhenPecNotEqualDdl;
    BigDecimal procLimitWhenRemLimitIsLessThan20k;
    BigDecimal procLimitWhenRemLimitIsLessThan20kLessThan10k;

    public OtherLimit() {
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public BigDecimal getStdLimit() {
        return stdLimit;
    }

    public void setStdLimit(BigDecimal stdLimit) {
        this.stdLimit = stdLimit;
    }

    public BigDecimal getAllowLimit() {
        return allowLimit;
    }

    public void setAllowLimit(BigDecimal allowLimit) {
        this.allowLimit = allowLimit;
    }

    public BigDecimal getLimitWhenPecEqualDdl() {
        return limitWhenPecEqualDdl;
    }

    public void setLimitWhenPecEqualDdl(BigDecimal limitWhenPecEqualDdl) {
        this.limitWhenPecEqualDdl = limitWhenPecEqualDdl;
    }

    public BigDecimal getLimitWhenPecNotEqualDdl() {
        return limitWhenPecNotEqualDdl;
    }

    public void setLimitWhenPecNotEqualDdl(BigDecimal limitWhenPecNotEqualDdl) {
        this.limitWhenPecNotEqualDdl = limitWhenPecNotEqualDdl;
    }

    public BigDecimal getProcLimitWhenRemLimitIsLessThan20k() {
        return procLimitWhenRemLimitIsLessThan20k;
    }

    public void setProcLimitWhenRemLimitIsLessThan20k(BigDecimal procLimitWhenRemLimitIsLessThan20k) {
        this.procLimitWhenRemLimitIsLessThan20k = procLimitWhenRemLimitIsLessThan20k;
    }

    public BigDecimal getProcLimitWhenRemLimitIsLessThan20kLessThan10k() {
        return procLimitWhenRemLimitIsLessThan20kLessThan10k;
    }

    public void setProcLimitWhenRemLimitIsLessThan20kLessThan10k(BigDecimal procLimitWhenRemLimitIsLessThan20kLessThan10k) {
        this.procLimitWhenRemLimitIsLessThan20kLessThan10k = procLimitWhenRemLimitIsLessThan20kLessThan10k;
    }
}
