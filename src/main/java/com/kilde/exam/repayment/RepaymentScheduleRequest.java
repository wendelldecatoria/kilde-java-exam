package com.kilde.exam.repayment;

import java.util.UUID;

public class RepaymentScheduleRequest {

    private UUID investmentId;
    private RepaymentScheduleStatus status;

    public RepaymentScheduleRequest() {}

    public UUID getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(UUID investmentId) {
        this.investmentId = investmentId;
    }

    public RepaymentScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(RepaymentScheduleStatus status) {
        this.status = status;
    }
}
