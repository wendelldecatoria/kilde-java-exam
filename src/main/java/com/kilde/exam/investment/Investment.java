package com.kilde.exam.investment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kilde.exam.audit.Auditable;
import com.kilde.exam.investor.Investor;
import com.kilde.exam.tranch.Tranch;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Optional;
import java.util.UUID;

@Entity
public class Investment extends Auditable {

    @Id
    @GeneratedValue
    @UuidGenerator  // NEW: Hibernate 6.5+ way to generate UUIDs
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    @JsonBackReference
    private Investor investor;

    @ManyToOne
    @JoinColumn(name = "tranch_id")
    @JsonBackReference
    private Tranch tranch;

    private Float amount;
    private Float fee;
    private Float netAmount;

    public Investment(Investor investor, Tranch tranch, Float amount, Float fee, Float netAmount) {
        this.investor = investor;
        this.tranch = tranch;
        this.amount = amount;
        this.fee = fee;
        this.netAmount = netAmount;
    }

    public Investment() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public Float getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Float netAmount) {
        this.netAmount = netAmount;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Tranch getTranch() {
        return tranch;
    }

    public void setTranch(Tranch tranch) {
        this.tranch = tranch;
    }
}
