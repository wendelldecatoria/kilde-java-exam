package com.kilde.exam.repayment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kilde.exam.audit.Auditable;
import com.kilde.exam.investment.Investment;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;
import java.time.Instant;

@Entity
public class RepaymentSchedule extends Auditable {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "investment_id")
    @JsonBackReference
    private Investment investment;

    private Integer period;
    private Instant dueDate;
    private Float interestAmount;
    private Float principalAmount;
    private Float borrowerFee;

    @Enumerated(EnumType.STRING)
    private RepaymentScheduleStatus status;

    public RepaymentSchedule(Investment investment, Instant dueDate, Float interestAmount, Float principalAmount, Float borrowerFee, RepaymentScheduleStatus status, Integer period) {
        this.investment = investment;
        this.dueDate = dueDate;
        this.interestAmount = interestAmount;
        this.principalAmount = principalAmount;
        this.borrowerFee = borrowerFee;
        this.status = status;
        this.period = period;
    }

    public RepaymentSchedule() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Investment getInvestment() {
        return investment;
    }

    public void setInvestment(Investment investment) {
        this.investment = investment;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public Float getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(Float interestAmount) {
        this.interestAmount = interestAmount;
    }

    public Float getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(Float principalAmount) {
        this.principalAmount = principalAmount;
    }

    public Float getBorrowerFee() {
        return borrowerFee;
    }

    public void setBorrowerFee(Float borrowerFee) {
        this.borrowerFee = borrowerFee;
    }

    public RepaymentScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(RepaymentScheduleStatus status) {
        this.status = status;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
