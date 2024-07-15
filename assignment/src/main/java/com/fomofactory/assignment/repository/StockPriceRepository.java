package com.fomofactory.assignment.repository;

import com.fomofactory.assignment.model.StockPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockPriceRepository extends MongoRepository<StockPrice, String> {
    List<StockPrice> findTop20BySymbolOrderByTimestampDesc(String symbol);
}