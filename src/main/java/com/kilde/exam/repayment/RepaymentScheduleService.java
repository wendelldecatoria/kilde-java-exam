package com.kilde.exam.repayment;

import com.kilde.exam.investment.Investment;
import com.kilde.exam.investment.InvestmentRepository;
import com.kilde.exam.tranch.Tranch;
import com.kilde.exam.tranch.TranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RepaymentScheduleService {

    @Autowired
    private RepaymentScheduleRepository repaymentRepository;

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private TranchRepository tranchRepository;

    @Value("${investment.fee-rate}")
    private Float feeRate;

    public List<RepaymentSchedule> getAllRepayments() {
        List<RepaymentSchedule> repayments = new ArrayList<>();
        repaymentRepository.findAll().forEach(repayments::add);
        return repayments;
    }

    public List<RepaymentSchedule> getRepaymentsByInvestment(UUID investmentId) {
        List<RepaymentSchedule> repayments = new ArrayList<>();
        repaymentRepository.findByInvestmentIdOrderByPeriod(investmentId).forEach(repayments::add);
        return repayments;
    }

    public RepaymentSchedule updateRepayment(UUID id, RepaymentScheduleRequest repaymentScheduleRequest) throws Exception {
        try {
            RepaymentSchedule repaymentSchedule = repaymentRepository.findById(id).orElseThrow(() -> new Exception("Repayment Schedule not found."));
            repaymentSchedule.setStatus(repaymentScheduleRequest.getStatus());
            repaymentRepository.save(repaymentSchedule);
            return repaymentSchedule;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<RepaymentSchedule> processRepayment(UUID investmentId) throws Exception {
        // TODO: block process request when there is already an existing schedule for the investment

        try {
            Investment investment = investmentRepository.findById(investmentId)
                    .orElseThrow(() -> new Exception("Investment not found."));

            UUID tranchId = investment.getTranch().getId();
            Tranch tranch = tranchRepository.findById(tranchId)
                    .orElseThrow(() -> new Exception("Tranch not found."));

            Float netAmount = investment.getNetAmount();
            Integer monthlyDuration = tranch.getDuration();
            Float annualInterestRate = tranch.getAnnualInterestRate();
            Float monthlyInterestRate = annualInterestRate / monthlyDuration;
            Float interestAmount = netAmount * monthlyInterestRate;
            LocalDate investmentDate = investment.getCreatedAt().atZone(ZoneId.systemDefault()).toLocalDate();


            List<RepaymentSchedule> schedule = new ArrayList<>();

            // iterate over the duration
            for (int i = 1; i <= monthlyDuration; i++) {
                Float principalAmount = (i == monthlyDuration) ? netAmount : BigDecimal.ZERO.floatValue();
                Float totalRepayment = interestAmount + principalAmount;
                Float borrowerFee = totalRepayment * feeRate;
                LocalDate dueDate = investmentDate.plusMonths(i);

                RepaymentSchedule repayment = new RepaymentSchedule();
                repayment.setDueDate(dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                repayment.setInterestAmount(interestAmount);
                repayment.setPrincipalAmount(principalAmount);
                repayment.setBorrowerFee(borrowerFee);
                repayment.setStatus(RepaymentScheduleStatus.PENDING);
                repayment.setInvestment(investment);
                repayment.setPeriod(i);

                schedule.add(repayment);
            }

            repaymentRepository.saveAll(schedule);
            return schedule;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
