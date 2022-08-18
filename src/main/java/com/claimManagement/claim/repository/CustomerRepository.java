package com.claimManagement.claim.repository;

import com.claimManagement.claim.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
    Optional<Customers> findByUserName(String username);
}