package com.kilde.exam.bond;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kilde.exam.audit.Auditable;
import com.kilde.exam.borrower.Borrower;
import com.kilde.exam.investment.Investment;
import com.kilde.exam.tranch.Tranch;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Bond extends Auditable {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    private String purpose;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    @JsonBackReference
    private Borrower borrower;

    @OneToMany(mappedBy = "bond", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Tranch> tranches = new ArrayList<>();

    public Bond(String name, Borrower borrower, String purpose) {
        this.name = name;
        this.borrower = borrower;
        this.purpose = purpose;
    }

    public Bond() {}

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

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public List<Tranch> getTranches() {
        return tranches;
    }

    public void setTranches(List<Tranch> tranches) {
        this.tranches = tranches;
    }
}
