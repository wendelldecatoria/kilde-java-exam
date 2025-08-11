package com.kilde.exam.borrower;

import com.kilde.exam.borrower.Borrower;
import com.kilde.exam.borrower.BorrowerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BorrowerService {
    
    @Autowired
    private BorrowerRepository borrowerRepository;

    public List<Borrower> getAllBorrowers() {
        List<Borrower> borrowers = new ArrayList<>();
        borrowerRepository.findAll().forEach(borrowers::add);
        return borrowers;
    }

    public Optional<Borrower> getBorrower(UUID id) {
        return borrowerRepository.findById(id);
    }

    public Borrower addBorrower(BorrowerRequest borrowerRequest) {
        try {
            Borrower borrower = new Borrower();
            borrower.setName(borrowerRequest.getName());
            borrower.setEmail(borrowerRequest.getEmail());
            return borrowerRepository.save(borrower);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Borrower updateBorrower(UUID id, BorrowerRequest borrowerRequest) throws Exception {
        try {
            Borrower borrower = borrowerRepository.findById(id).orElseThrow(() -> new Exception("Borrower not found."));
            borrower.setName(borrowerRequest.getName());
            borrower.setEmail(borrowerRequest.getEmail());

            borrowerRepository.save(borrower);
            return borrower;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
