package com.claimManagement.claim.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
//import java.util.List;

@Entity
@Table(name = "claim_management")
public class Claim {
    /** primary key	*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "status")
    private String status;

    public Claim() {
    }

    public Claim(String firstName, String lastName, Double amount, String email, Long phoneNumber, String policyNumber, String status, List<FileEntity> fileEntityList, Customers customers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.amount = amount;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.policyNumber = policyNumber;
        this.fileEntityList = fileEntityList;
        this.customers = customers;
        this.status=status;
    }

    public List<FileEntity> getFileEntityList() {
        return fileEntityList;
    }

    public void setFileEntityList(List<FileEntity> fileEntityList) {
        this.fileEntityList = fileEntityList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Long getId() {
        return id;
    }


    @OneToMany(mappedBy="claim")
    private List<FileEntity> fileEntityList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customers customers;
}