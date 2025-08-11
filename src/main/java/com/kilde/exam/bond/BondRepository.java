package com.kilde.exam.bond;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BondRepository extends JpaRepository<Bond, UUID> {
    Optional<Bond> findById(UUID id);
}
