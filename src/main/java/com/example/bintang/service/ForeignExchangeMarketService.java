package com.example.bintang.service;

import com.example.bintang.entity.ForeignExchangeMarket;
import com.example.bintang.repository.ForeignExchangeMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForeignExchangeMarketService {
    @Autowired
    ForeignExchangeMarketRepository foreignExchangeMarketRepository;

    public ForeignExchangeMarket getExchangePrice(String currencyTo) {
        return foreignExchangeMarketRepository.findByCurrencyTo(currencyTo);
    }

    public ForeignExchangeMarket addExchangeMarket(ForeignExchangeMarket market) {
        return foreignExchangeMarketRepository.save(market);
    }
}
