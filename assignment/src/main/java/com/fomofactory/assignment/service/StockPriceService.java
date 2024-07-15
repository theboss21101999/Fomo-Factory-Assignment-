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

@Service
public class StockPriceService {
    @Autowired
    private StockPriceRepository repository;

    private final String API_URL = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum&vs_currencies=usd";

    @Scheduled(fixedRate = 30000) // Poll every 30 seconds
    public void fetchAndStorePrices() {
        RestTemplate restTemplate = new RestTemplate();
        StockPrice[] prices = restTemplate.getForObject(API_URL, StockPrice[].class);

        if (prices != null) {
            repository.saveAll(Arrays.asList(prices));
        }
    }

    public List<StockPrice> getRecentPrices(String symbol) {
        return repository.findTop20BySymbolOrderByTimestampDesc(symbol);
    }
}