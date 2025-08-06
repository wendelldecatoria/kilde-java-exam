package com.kilde.exam.investment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface InvestmentRepository extends JpaRepository<Investment, UUID> {
    Optional<Investment> findById(UUID id);

    @Query("SELECT SUM(i.amount) FROM Investment i WHERE i.tranch.id = :tranchId AND i.investor.id = :investorId")
    Float sumAmountByTranchIdAndInvestorId(@Param("tranchId") UUID tranchId, @Param("investorId") UUID investorId);

    @Query("SELECT SUM(i.amount) FROM Investment i WHERE i.tranch.id = :tranchId")
    Float sumAmountByTranchId(@Param("tranchId") UUID tranchId);
}
