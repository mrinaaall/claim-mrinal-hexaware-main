package com.claimManagement.claim.service;

import com.claimManagement.claim.customersDto.LoginDto;
import com.claimManagement.claim.model.Customers;

public interface CustomerService {
    Customers registerCustomer(Customers customers);
    Customers login(LoginDto loginDto);
}
