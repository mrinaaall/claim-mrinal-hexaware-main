package com.claimManagement.claim.contoller;

import com.claimManagement.claim.model.Claim;
import com.claimManagement.claim.service.ClaimService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ClaimController {

    @Autowired
    ClaimService claimService;

    //    Logger logger = Logger.getLogger(ClaimManagementController.class.getName());
    Logger logger = LogManager.getLogger(ClaimController.class.getName());

    @GetMapping("/claim/{id}")
    public ResponseEntity<List<Claim>> getAll(@PathVariable Long id) {
        logger.debug("getAll - Entry");
        try {
            logger.info("Trying to get all the claims...");
            List<Claim> claimList = claimService.getAllClaims(id);
            if (!claimList.isEmpty()) //important
            {
                return new ResponseEntity<>(claimService.getAllClaims(id), HttpStatus.FOUND);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            logger.error("getAll - could not get all the claims - " + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/claimDetails/{id}")
    public ResponseEntity<Claim> getClaim(@PathVariable(value = "id") Long id) {
        logger.debug("getClaim - Entry");
        logger.info("Trying to get the claim...");
        Optional<Claim> claimData = claimService.getClaim(id);
        return claimData.map(claim -> new ResponseEntity<>(claim, HttpStatus.OK)).orElseGet(() -> {
            logger.error("getClaim - error getting claim with specified ID");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }

    @PutMapping("/updateClaim/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable("id") long id, @RequestBody Claim claim, @RequestHeader("userName") String userName) {
        logger.debug("updateClaim - Entry");
        logger.warn("The claim will only update if the username is correct...");
        try {
            Claim claim1 = claimService.updateClaim(claim, id, userName);
            if (null != claim1) {
                logger.debug("updateClaim - Exit");
                return new ResponseEntity<>(claim1, HttpStatus.CREATED);
            }
            else {
                logger.error("updateClaim - Username did not match");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            logger.error("updateClaim - could not find the claimData for the given ID");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Long> deleteClaim(@PathVariable("id") Long id, @RequestHeader("userName") String userName){
        logger.debug("deleteClaim - Entry");
        try {
            logger.info("Trying to delete the claim...");
            Long deletedId = claimService.deleteClaim(id, userName); // stores the ID returned from delteClaimID
            if (null != deletedId) return new ResponseEntity<>(deletedId, HttpStatus.OK);
            else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("deleteClaim - error deleting claim - " + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/claim/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Claim> createClaim(@RequestBody Claim claim, @PathVariable Long id) {
        logger.debug("createClaim - Entry");
        try {
            logger.info("Trying to save the claim...");
            return new ResponseEntity<>(claimService.saveClaim(claim, id), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("createClaim - error saving claim - " + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
