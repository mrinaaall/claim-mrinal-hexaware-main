package com.claimManagement.claim.model;

import javax.persistence.*;

import com.claimManagement.claim.model.Claim;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String fileId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "DIR_location", nullable = false)
    private String DIR_location;


//    @Column(name = "data", nullable = false)
//    @Lob
//    private byte[] data;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "claim_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Claim claim;

    public FileEntity(){
    }

    public FileEntity(String name, String type, String DIR_location, Claim claim) {
        this.name = name;
        this.type = type;
        this.DIR_location = DIR_location;
        this.claim = claim;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDIR_location() {
        return DIR_location;
    }

    public void setDIR_location(String DIR_location) {
        this.DIR_location = DIR_location;
    }
}