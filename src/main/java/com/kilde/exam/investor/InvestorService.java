package com.kilde.exam.investor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvestorService {

    @Autowired
    private InvestorRepository investorRepository;

    public List<Investor> getAllInvestors() {
        List<Investor> investors = new ArrayList<>();
        investorRepository.findAll().forEach(investors::add);
        return investors;
    }

    public Optional<Investor> getInvestor(UUID id) {
        return investorRepository.findById(id);
    }

    public Investor addInvestor(InvestorRequest investorRequest) {
        try {
            Investor investor = new Investor();
            investor.setName(investorRequest.getName());
            investor.setEmail(investorRequest.getEmail());

            return investorRepository.save(investor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Investor updateInvestor(UUID id, InvestorRequest investorRequest) throws Exception {
        try {
            Investor investor = investorRepository.findById(id).orElseThrow(() -> new Exception("Investor not found."));
            investor.setName(investorRequest.getName());
            investor.setEmail(investorRequest.getEmail());

            investorRepository.save(investor);
            return investor;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
