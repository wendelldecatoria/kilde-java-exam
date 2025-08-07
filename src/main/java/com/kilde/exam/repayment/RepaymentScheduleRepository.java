package com.kilde.exam.repayment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepaymentScheduleRepository extends JpaRepository<RepaymentSchedule, UUID> {
    Optional<RepaymentSchedule> findById(UUID id);

    List<RepaymentSchedule> findByInvestmentIdOrderByPeriod(UUID investmentId);
}
