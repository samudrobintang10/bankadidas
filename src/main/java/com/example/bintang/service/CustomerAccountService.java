package com.example.bintang.service;

import com.example.bintang.dto.CustomerAccountRequest;
import com.example.bintang.entity.Customer;
import com.example.bintang.entity.CustomerAccount;
import com.example.bintang.repository.CustomerAccountRepository;
import com.example.bintang.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerAccountService {

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public CustomerAccount createCustomerAccount(CustomerAccountRequest request) {
        // Cek apakah customer ada
        Optional<Customer> customerOpt = customerRepository.findById(request.getCustomerId());
        if (customerOpt.isEmpty()) {
            throw new IllegalArgumentException("Customer dengan ID " + request.getCustomerId() + " tidak ditemukan.");
        }

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomer(customerOpt.get());
        customerAccount.setType(request.getType());
        customerAccount.setAccount(request.getAccount());
        customerAccount.setBalance(String.valueOf(request.getBalance()));

        customerAccountRepository.save(customerAccount);

        return customerAccount;

    }

    public List<CustomerAccountRequest> getAllByCustomerId(Integer param) {
        List<CustomerAccount> response = customerAccountRepository.findAllByCustomerId(param);
        List<CustomerAccountRequest> responseDTO = new ArrayList<>();

        for (CustomerAccount account : response) {
            CustomerAccountRequest dto = new CustomerAccountRequest();
            dto.setCustomerId(account.getCustomer().getId());
            dto.setAccount(account.getAccount());
            dto.setType(account.getType());
            dto.setBalance(Float.valueOf(account.getBalance()));

            responseDTO.add(dto);
        }
        return responseDTO;
    }

    public CustomerAccount getBalanceByCustomerAccount(String param) {
        CustomerAccount response = customerAccountRepository.findByAccount(param);

        return response;
    }

    public CustomerAccount addBalance(String param) {
        CustomerAccount response = customerAccountRepository.findByAccount(param);

        return response;
    }
}
