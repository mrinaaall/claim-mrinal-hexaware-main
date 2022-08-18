package com.claimManagement.claim.repository;

import com.claimManagement.claim.model.FileEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, String> {
    List<FileEntity> findByClaimId(Long claimId);
}