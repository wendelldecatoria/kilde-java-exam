package com.kilde.exam.tranch;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kilde.exam.audit.Auditable;
import com.kilde.exam.investment.Investment;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Tranch extends Auditable {

    @Id
    @GeneratedValue
    @UuidGenerator  // NEW: Hibernate 6.5+ way to generate UUIDs
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    private Float annualInterestRate;
    private Integer duration;
    private Float minInvestment;
    private Float maxInvestment;
    private Float maxPerInvestor;

    @Enumerated(EnumType.STRING)
    private TranchStatus status;

    @OneToMany(mappedBy = "tranch", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Investment> investments = new ArrayList<>();

    public Tranch(
            String name,
            Float annual_interest_rate,kil
            Integer duration,
            Float min_investment,
            Float max_investment,
            Float max_per_investor,
            TranchStatus status) {
        this.name = name;
        this.annualInterestRate = annual_interest_rate;
        this.duration = duration;
        this.minInvestment = min_investment;
        this.maxInvestment = max_investment;
        this.maxPerInvestor = max_per_investor;
        this.status = status;
    }

    public Tranch() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }
}
