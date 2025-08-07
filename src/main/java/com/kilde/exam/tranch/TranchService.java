package com.kilde.exam.tranch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TranchService {

    @Autowired
    private TranchRepository tranchRepository;

    public List<Tranch> getAllTranches() {
        List<Tranch> tranches = new ArrayList<>();
        tranchRepository.findAll().forEach(tranches::add);
        return tranches;
    }

    public Optional<Tranch> getTranch(UUID id) {
        return tranchRepository.findById(id);
    }

    public Tranch addTranch(Tranch tranch) {
        try {
            tranchRepository.save(tranch);
            return tranch;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Tranch updateTranch(UUID id, TranchRequest tranchRequest) throws Exception {
        try {
            Tranch tranch = tranchRepository.findById(id).orElseThrow(() -> new Exception("Tranch not found."));

            tranch.setName(tranchRequest.getName());
            tranch.setAnnualInterestRate(tranchRequest.getAnnualInterestRate());
            tranch.setDuration(tranchRequest.getDuration());
            tranch.setMinInvestment(tranchRequest.getMinInvestment());
            tranch.setMaxInvestment(tranchRequest.getMaxInvestment());
            tranch.setMaxPerInvestor(tranchRequest.getMaxPerInvestor());

            tranchRepository.save(tranch);
            return tranch;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
