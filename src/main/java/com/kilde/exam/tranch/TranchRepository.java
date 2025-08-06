package com.kilde.exam.tranch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TranchRepository extends JpaRepository<Tranch, UUID> {
    Optional<Tranch> findById(UUID id);
}
