package com.example.bintang.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "foreign_exchange_markets")
public class ForeignExchangeMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foreign_exchange_market_seq")
    @SequenceGenerator(name = "foreign_exchange_market_seq", sequenceName = "foreign_exchange_markets_id_seq", allocationSize = 1)
    @Schema(hidden = true)
    private Integer id;

    @Column(name = "currency_from")
    private String currencyFrom;

    @Column(name = "currency_to")
    private String currencyTo;

    @Column(name = "exchange_price")
    private Float exchangePrice;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}

