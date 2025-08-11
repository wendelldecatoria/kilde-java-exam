package com.kilde.exam.bond;

import com.kilde.exam.borrower.Borrower;
import com.kilde.exam.borrower.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BondService {

    @Autowired
    private BondRepository bondRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    public List<Bond> getAllBonds() {
        List<Bond> bonds = new ArrayList<>();
        bondRepository.findAll().forEach(bonds::add);
        return bonds;
    }

    public Optional<Bond> getBond(UUID id) {
        return bondRepository.findById(id);
    }

    public Bond createBond(BondRequest bondRequest) throws Exception {
        try {

            Borrower borrower = borrowerRepository.findById(bondRequest.getBorrowerId())
                    .orElseThrow(() -> new Exception("Bond not found."));

            Bond bond = new Bond();
            bond.setBorrower(borrower);
            bond.setName(bondRequest.getName());
            bond.setPurpose(bondRequest.getPurpose());

            return bondRepository.save(bond);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Bond updateBond(UUID id, BondRequest bondRequest) throws Exception {
        Bond bond = bondRepository.findById(id).orElseThrow(() -> new Exception("Bond not found."));
        bond.setName(bondRequest.getName());
        bond.setPurpose(bondRequest.getPurpose());

        return bondRepository.save(bond);
    }
}
