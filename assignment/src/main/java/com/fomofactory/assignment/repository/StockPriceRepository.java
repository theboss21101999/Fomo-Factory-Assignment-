package com.fomofactory.assignment.repository;

import com.fomofactory.assignment.model.StockPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 *Author: Bojja Srikar
 */
public interface StockPriceRepository extends MongoRepository<StockPrice, String> {
    List<StockPrice> findTop20BySymbolOrderByTimestampDesc(String symbol);
}
