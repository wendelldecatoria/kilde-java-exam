package com.kilde.exam.investment;

import com.kilde.exam.investor.Investor;
import com.kilde.exam.investor.InvestorRepository;
import com.kilde.exam.tranch.Tranch;
import com.kilde.exam.tranch.TranchRepository;
import com.kilde.exam.tranch.TranchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private TranchRepository tranchRepository;

    @Value("${investment.fee-rate}")
    private Float feeRate;

    public List<Investment> getAllInvestments() {
        List<Investment> investments = new ArrayList<>();
        investmentRepository.findAll().forEach(investments::add);
        return investments;
    }

    public Optional<Investment> getInvestment(UUID id) {
        return investmentRepository.findById(id);
    }

    public Investment addInvestment(InvestmentRequest investmentRequest) throws Exception {
        Tranch tranch = tranchRepository.findById(investmentRequest.getTranchId())
                .orElseThrow(() -> new Exception("Tranch not found."));

        // Validate Tranch Status
        if (tranch.getStatus() != TranchStatus.OPEN) {
            throw new Exception("Tranch Status not OPEN.");
        }

        Investor investor = investorRepository.findById(investmentRequest.getInvestorId())
                .orElseThrow(() -> new Exception("Investor not found exception."));

        Float maxInvestment = tranch.getMaxInvestment();
        Float maxPerInvestor = tranch.getMaxPerInvestor();
        float summedAmountByTranchId = Optional.ofNullable(investmentRepository.sumAmountByTranchId(tranch.getId())).orElse(0.0f)
                + investmentRequest.getAmount();
        float summedAmountByTranchIdAndInvestorId = Optional.ofNullable(investmentRepository.sumAmountByTranchIdAndInvestorId(tranch.getId(), investor.getId())).orElse(0.0f)
                + investmentRequest.getAmount();

        if (summedAmountByTranchId > maxInvestment) {
            throw new Exception("Maximum Investment amount reached!");
        }

        if (summedAmountByTranchIdAndInvestorId > maxPerInvestor) {
            throw new Exception("Maximum Investment amount per Invester reached!");
        }

        // Calculate fee and net amount
        Float investorFee = investmentRequest.getAmount() * this.feeRate;
        Float netAmount = investmentRequest.getAmount() - investorFee;

        // Create and save the investment
        Investment investment = new Investment();
        investment.setAmount(investmentRequest.getAmount());
        investment.setFee(investorFee);
        investment.setNetAmount(netAmount);
        investment.setInvestor(investor);
        investment.setTranch(tranch);

        return investmentRepository.save(investment); // only saved if both exist
    }
}
