package com.fomofactory.assignment.controller;
import com.fomofactory.assignment.model.StockPrice;
import com.fomofactory.assignment.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api") // Adding base path for the controller
public class PriceDataController {

    @Autowired
    private StockPriceService service;

    @GetMapping("/prices")
    public ResponseEntity<List<StockPrice>> getPrices(@RequestParam String symbol) {
        List<StockPrice> prices = service.getRecentPrices(symbol);

        if (prices.isEmpty()) {
            return ResponseEntity.status(404).body(prices); // Return 404 if no prices found
        }

        return ResponseEntity.ok(prices); // Return 200 with prices
    }
}