package com.kilde.exam.borrower;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BorrowerRepository extends JpaRepository<Borrower, UUID> {
    Optional<Borrower> findById(UUID id);
}
