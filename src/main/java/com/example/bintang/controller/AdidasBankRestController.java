package com.example.bintang.controller;

import com.example.bintang.dto.CustomerAccountRequest;
import com.example.bintang.entity.Customer;
import com.example.bintang.entity.CustomerAccount;
import com.example.bintang.entity.ForeignExchangeMarket;
import com.example.bintang.entity.Transaction;
import com.example.bintang.service.CustomerAccountService;
import com.example.bintang.service.CustomerService;
import com.example.bintang.service.ForeignExchangeMarketService;
import com.example.bintang.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdidasBankRestController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerAccountService customerAccountService;

    @Autowired
    ForeignExchangeMarketService foreignExchangeMarketService;

    @Autowired
    TransactionService transactionService;

    // Creator: Dewi Ilmi Rizqi
    @PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer request){
        Customer response = request;
        response = customerService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCustomer")
    public ResponseEntity<Customer> getCustomer(@RequestParam String param) {
        Customer response = customerService.getCustomer(param);

        return ResponseEntity.ok(response);
    }

    // Creator: Faisal Aulia Ghani
    @PostMapping("/createCustomerAccount")
    public ResponseEntity<CustomerAccount> createCustomerAccount(@RequestBody CustomerAccountRequest request) {
        CustomerAccount customerAccount = customerAccountService.createCustomerAccount(request);
        return ResponseEntity.ok(customerAccount);
    }

    @GetMapping("/getAllCustomerAccountByCustomerId")
    public ResponseEntity<List<CustomerAccountRequest>> getAllCustomerAccountByCustomerId(@RequestParam Integer param) {
        List<CustomerAccountRequest> response = customerAccountService.getAllByCustomerId(param);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBalanceByCustomerAccount")
    public ResponseEntity<CustomerAccount> getBalanceByCustomerAccount(@RequestParam String param) {
        CustomerAccount response = customerAccountService.getBalanceByCustomerAccount(param);

        return ResponseEntity.ok(response);
    }

    // Creator: Hafizh Daffa Septianto
    @PostMapping ("/createForeignExchangeMarket")
    public ResponseEntity<ForeignExchangeMarket> addExchangeMarket(@RequestBody ForeignExchangeMarket market) {
        ForeignExchangeMarket createdMarket = foreignExchangeMarketService.addExchangeMarket(market);
        return ResponseEntity.ok(createdMarket);
    }

    @GetMapping("/getForeignExchangeMarket")
    public ResponseEntity<ForeignExchangeMarket> getExchangePrice(@RequestParam String currencyTo) {
        ForeignExchangeMarket exchangePrice = foreignExchangeMarketService.getExchangePrice(currencyTo);

        if (exchangePrice != null) {
            return ResponseEntity.ok(exchangePrice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Creator: Bintang Samudro
    @GetMapping("/getAllTransactionByCustomerId")
    public ResponseEntity<List<Transaction>> getAllTransactionByCustomerId(@RequestParam Integer param) {
        List<Transaction> response = transactionService.getAllByCustomerId(param);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/createTransactionExchange")
    public ResponseEntity<?> createTransactionExchange(@RequestBody Transaction request) {
        try {
            Transaction response = transactionService.createTransaction(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Terjadi kesalahan pada server");
        }
    }
}
