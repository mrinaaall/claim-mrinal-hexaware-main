package com.claimManagement.claim.service;

import com.claimManagement.claim.model.Claim;

import java.util.List;
import java.util.Optional;

public interface ClaimService {
    Claim saveClaim(Claim claim, Long id);
    List<Claim> getAllClaims(Long id);
    Optional<Claim> getClaim(Long id);
    Long deleteClaim(Long id, String userName);
    Claim updateClaim(Claim claim, Long id, String userName);
}

