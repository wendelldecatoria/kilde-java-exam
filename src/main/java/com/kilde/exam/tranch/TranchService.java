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
        tranchRepository.save(tranch);
        return tranch;
    }

    public Tranch updateTranch(Tranch tranch) {
        tranchRepository.save(tranch);
        return tranch;
    }
}
