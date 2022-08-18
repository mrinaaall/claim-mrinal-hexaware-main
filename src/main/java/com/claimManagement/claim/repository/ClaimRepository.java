package com.claimManagement.claim.repository;

import com.claimManagement.claim.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findClaimByCustomersId(Long userId);
    List<Claim> findClaimByCustomersUserName(String userName);
}
