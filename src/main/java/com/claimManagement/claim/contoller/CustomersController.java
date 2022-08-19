package com.claimManagement.claim.contoller;

import com.claimManagement.claim.customersDto.LoginDto;
import com.claimManagement.claim.model.Customers;
import com.claimManagement.claim.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CustomersController {
    @Autowired
    CustomerService customerService;
    Logger logger = LogManager.getLogger(CustomersController.class.getName());
    @PostMapping(value = "/register")
    public ResponseEntity<Customers> register(@RequestBody Customers customers){
        logger.debug("entered in the register method");
        try{
            return new ResponseEntity<>(customerService.registerCustomer(customers), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error = "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/login")
     public ResponseEntity<Customers> login(@RequestBody LoginDto loginDto){
         logger.debug("entered in the login method");
         try {
            //  return new ResponseEntity<>(customerService.login(loginDto), HttpStatus.FOUND);
             Customers customers = customerService.login(loginDto);
             if (null != customers) return new ResponseEntity<>(customerService.login(loginDto), HttpStatus.FOUND);
             else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
         } catch (Exception e){
             return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }
}
