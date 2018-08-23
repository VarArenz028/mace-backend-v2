package com.basicauth.domain;

import java.math.BigDecimal;

public class Room {

    private int PlanCode;
    private String PlanDesc;
    private BigDecimal RateAmount;
    private BigDecimal RuvMultiplier;

    public int getPlanCode() {
        return PlanCode;
    }

    public void setPlanCode(int planCode) {
        PlanCode = planCode;
    }

    public String getPlanDesc() {
        return PlanDesc;
    }

    public void setPlanDesc(String planDesc) {
        PlanDesc = planDesc;
    }

    public BigDecimal getRateAmount() {
        return RateAmount;
    }

    public void setRateAmount(BigDecimal rateAmount) {
        RateAmount = rateAmount;
    }

    public BigDecimal getRuvMultiplier() {
        return RuvMultiplier;
    }

    public void setRuvMultiplier(BigDecimal ruvMultiplier) {
        RuvMultiplier = ruvMultiplier;
    }
}
