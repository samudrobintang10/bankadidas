package com.example.bintang.service;

import com.example.bintang.entity.Customer;
import com.example.bintang.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer createUser(Customer request){
        Customer response = request;
        customerRepository.save(response);
        return response;
    }

    public Customer getCustomer(String param) {
        Customer user = customerRepository.findById(Integer.valueOf(param)).get();

        return user;
    }
}
