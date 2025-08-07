package com.kilde.exam.tranch;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class TranchRequest {
    private String name;
    private Float annualInterestRate;
    private Integer duration;
    private Float minInvestment;
    private Float maxInvestment;
    private Float maxPerInvestor;

    @Enumerated(EnumType.STRING)
    private TranchStatus status;

    public TranchRequest(String name, Float annualInterestRate, Integer duration, Float minInvestment, Float maxInvestment, Float maxPerInvestor, TranchStatus status) {
        this.name = name;
        this.annualInterestRate = annualInterestRate;
        this.duration = duration;
        this.minInvestment = minInvestment;
        this.maxInvestment = maxInvestment;
        this.maxPerInvestor = maxPerInvestor;
        this.status = status;
    }

    public TranchRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(Float annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Float getMinInvestment() {
        return minInvestment;
    }

    public void setMinInvestment(Float minInvestment) {
        this.minInvestment = minInvestment;
    }

    public Float getMaxInvestment() {
        return maxInvestment;
    }

    public void setMaxInvestment(Float maxInvestment) {
        this.maxInvestment = maxInvestment;
    }

    public Float getMaxPerInvestor() {
        return maxPerInvestor;
    }

    public void setMaxPerInvestor(Float maxPerInvestor) {
        this.maxPerInvestor = maxPerInvestor;
    }

    public TranchStatus getStatus() {
        return status;
    }

    public void setStatus(TranchStatus status) {
        this.status = status;
    }
}
