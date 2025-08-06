package com.kilde.exam.investment;

import java.util.UUID;

public class InvestmentRequest {

    private UUID investorId;
    private UUID tranchId;
    Float amount;

    public InvestmentRequest(UUID investorId, UUID tranchId, Float amount) {
        this.investorId = investorId;
        this.tranchId = tranchId;
        this.amount = amount;
    }

    public InvestmentRequest() {}

    public UUID getInvestorId() {
        return investorId;
    }

    public void setInvestorId(UUID investorId) {
        this.investorId = investorId;
    }

    public UUID getTranchId() {
        return tranchId;
    }

    public void setTranchId(UUID tranchId) {
        this.tranchId = tranchId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
