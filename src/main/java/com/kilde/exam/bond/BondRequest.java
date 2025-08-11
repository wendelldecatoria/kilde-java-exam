package com.kilde.exam.bond;

import java.util.UUID;

public class BondRequest {

    private UUID borrowerId;
    private String name;
    private String purpose;

    public BondRequest(UUID borrower_id, String name, String purpose) {
        this.borrowerId = borrower_id;
        this.name = name;
        this.purpose = purpose;
    }

    public BondRequest() {}

    public UUID getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(UUID borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
