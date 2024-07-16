package com.fomofactory.assignment.controller;


import com.fomofactory.assignment.model.StockPrice;
import com.fomofactory.assignment.service.StockPriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *Author: Bojja Srikar
 */
@RestController
@RequestMapping(value = "/api")
public class StockPriceDataController {


    private final StockPriceService service;

    public StockPriceDataController(StockPriceService service) {
        this.service = service;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/prices/{symbol}")
    public ResponseEntity<List<StockPrice>> getPrices(@PathVariable String symbol) {
        List<StockPrice> prices = service.getRecentPrices(symbol);

        if (prices.isEmpty()) {
            return ResponseEntity.status(404).body(prices); // Return 404 if no prices found
        }

        return ResponseEntity.ok(prices); // Return 200 with prices
    }
}