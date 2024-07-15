package com.fomofactory.assignment.service;

import com.fomofactory.assignment.model.StockPrice;
import com.fomofactory.assignment.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Author: Bojja Srikar
 */

@Service
public class StockPriceService {

    @Autowired
    private StockPriceRepository repository;

    private final String API_URL = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum&vs_currencies=usd";

    @Scheduled(fixedRate = 30000) // Poll every 30 seconds
    public void fetchAndStorePrices() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            Map<String, Map<String, Object>> prices = restTemplate.getForObject(API_URL, Map.class);
            if (prices != null) {
                prices.forEach((symbol, value) -> {
                    Object priceObj = value.get("usd");
                    Double price = priceObj instanceof Double ? (Double) priceObj : ((Integer) priceObj).doubleValue();
                    StockPrice stockPrice = new StockPrice(symbol, price, LocalDateTime.now());
                    repository.save(stockPrice);
                });
            }
        } catch (Exception e) {
            System.err.println("Error fetching prices: " + e.getMessage());
        }
    }

    public List<StockPrice> getRecentPrices(String symbol) {
        List<StockPrice> prices = repository.findTop20BySymbolOrderByTimestampDesc(symbol);
        return prices;
    }
}
