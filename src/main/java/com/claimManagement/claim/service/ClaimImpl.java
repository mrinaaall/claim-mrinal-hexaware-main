package com.claimManagement.claim.service;

import com.claimManagement.claim.contoller.ClaimController;
import com.claimManagement.claim.model.Claim;
import com.claimManagement.claim.model.Customers;
import com.claimManagement.claim.repository.ClaimRepository;
import com.claimManagement.claim.repository.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimImpl implements ClaimService{
    private final ClaimRepository claimRepository;
    private final CustomerRepository customerRepository;

    Logger logger = LogManager.getLogger(ClaimController.class.getName());


    public ClaimImpl(ClaimRepository claimRepository, CustomerRepository customerRepository) {
        this.claimRepository = claimRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Claim saveClaim(Claim claim, Long id) {
        logger.debug("saveClaim(Service Impl) - Entry");
        Customers customers = customerRepository.findById(id).orElse(null);
        Claim claim1 = new Claim(claim.getFirstName(), claim.getLastName(), claim.getAmount(), claim.getEmail(), claim.getPhoneNumber(), claim.getPolicyNumber(), claim.getStatus(), claim.getFileEntityList(), customers);
        claimRepository.save(claim1);
        logger.info("Claim Saved.");
        return claim1;
    }

    @Override
    public List<Claim> getAllClaims(Long id) {
        logger.debug("updateClaim(Service Impl) - Entry");
        return claimRepository.findClaimByCustomersId(id);
    }

    @Override
    public Optional<Claim> getClaim(Long id) {
        logger.debug("getClaim(Service Impl) - Entry");
        return claimRepository.findById(id);
    }

    @Override
    public Long deleteClaim(Long id, String userName) {
        logger.debug("deleteClaim(Service Impl) - Entry");
        List<Claim> claimList = claimRepository.findClaimByCustomersUserName(userName); // ManyToOne: helps return all claims made by username
        boolean isPresent = claimList.stream().anyMatch(o->o.getId().equals(id)); // Stream operation: iterate through the stream to find matches
        if (isPresent) {
            claimRepository.deleteById(id);
            logger.info("Claim deleted...");
            logger.debug("deleteClaim(Service Impl) - Exit");
            return id;
        } else {
            return null;
        }
    }

    @Override
    public Claim updateClaim(Claim claim, Long id, String userName) {
        logger.debug("updateClaim(Service Impl) - Entry");
        List<Claim> claimList = claimRepository.findClaimByCustomersUserName(userName);
        logger.info("claimList = " + claimList);
        boolean isPresent = claimList.stream().anyMatch(o -> o.getId().equals(id));
        if (isPresent) {
            Claim oldClaim = claimRepository.findById(id).orElse(null);
            logger.info("updateClaim - verified username");
            logger.info("updateClaim - trying to update claim...");
            assert oldClaim != null;
            oldClaim.setFirstName(claim.getFirstName());
            oldClaim.setLastName(claim.getLastName());
            oldClaim.setAmount(claim.getAmount());
            oldClaim.setEmail(claim.getEmail());
            oldClaim.setPhoneNumber(claim.getPhoneNumber());
            oldClaim.setPolicyNumber(claim.getPolicyNumber());
            claimRepository.save(oldClaim);
            logger.info("Claim Saved.");
            return oldClaim;
        } else {
            return null;
        }
    }
}
