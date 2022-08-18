package com.claimManagement.claim.service;

import com.claimManagement.claim.customersDto.LoginDto;
import com.claimManagement.claim.model.Customers;
import com.claimManagement.claim.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerImpl implements CustomerService {

    Logger logger = LogManager.getLogger(CustomerImpl.class.getName());
    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Customers registerCustomer(Customers customers) {
        logger.debug("entered in the registerCustomer");
        // check if user already exists
        Optional<Customers> customers1 = customerRepository.findByUserName(customers.getUserName());
        if (customers1.isPresent()) {
            logger.error("user already exists");
            return null;
        }

        try {
            logger.info("trying to register customer");
            customerRepository.save(customers);
            logger.info("registration complete");
            return customers;
        } catch (Exception e){
            logger.error("registration was unsuccessful");
            return null;
        }
    }

    @Override
    public Customers login(LoginDto loginDto) {
        try {
            Optional<Customers> customers = customerRepository.findByUserName(loginDto.getUserName());
            if (customers.isPresent()){
                String userName = customers.get().getUserName();
                String password = customers.get().getPassword();
                if (userName.equals(loginDto.getUserName()) && password.equals(loginDto.getPassword())){
                    return customers.orElse(null);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e){
            logger.error("login was unsuccessful");
            return null;
        }
    }
}
