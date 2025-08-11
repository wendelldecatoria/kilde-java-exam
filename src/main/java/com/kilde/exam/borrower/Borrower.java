package com.kilde.exam.borrower;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kilde.exam.audit.Auditable;
import com.kilde.exam.bond.Bond;
import com.kilde.exam.investment.Investment;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Borrower extends Auditable {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Bond>  bonds = new ArrayList<>();

    public Borrower(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Borrower() {}

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

    public List<Bond> getBonds() {
        return bonds;
    }

    public void setBonds(List<Bond> bonds) {
        this.bonds = bonds;
    }
}
