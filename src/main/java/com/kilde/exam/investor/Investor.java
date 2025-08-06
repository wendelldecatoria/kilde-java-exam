package com.kilde.exam.investor;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kilde.exam.audit.Auditable;
import com.kilde.exam.investment.Investment;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Investor extends Auditable {

    @Id
    @GeneratedValue
    @UuidGenerator  // NEW: Hibernate 6.5+ way to generate UUIDs
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Investment> investments = new ArrayList<>();

    public Investor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Investor() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }
}
