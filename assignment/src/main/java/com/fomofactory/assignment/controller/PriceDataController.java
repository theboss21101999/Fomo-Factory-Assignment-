package com.fomofactory.assignment.controller;
import com.fomofactory.assignment.model.StockPrice;
import com.fomofactory.assignment.repository.StockPriceRepository;
import com.fomofactory.assignment.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PriceDataController {
    @Autowired
    private StockPriceService service;

    @GetMapping("/api/prices")
    public List<StockPrice> getPrices(@RequestParam String symbol) {
        return service.getRecentPrices(symbol);
    }
}