package com.fomofactory.assignment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "stockPrices")
public class StockPrice {
    @Id
    private String id;
    private String symbol;
    private double price;
    private LocalDateTime timestamp;

    public StockPrice() {}

    public StockPrice(String symbol, double price, LocalDateTime timestamp) {
        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}