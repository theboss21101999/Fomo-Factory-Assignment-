package com.fomofactory.assignment.service;

import com.fomofactory.assignment.model.StockPrice;
import com.fomofactory.assignment.repository.StockPriceRepository;
import jakarta.annotation.PostConstruct;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.spring.annotations.Recurring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StockPriceService {

    @Autowired
    private StockPriceRepository repository;

    @Autowired
    private JobScheduler jobScheduler;

    private final String API_URL = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum&vs_currencies=usd";

    @PostConstruct
    public void scheduleStartupJob() {
        Instant someTimeFromNow = Instant.now().plusSeconds(30);
        jobScheduler.schedule(someTimeFromNow, this::fetchAndStorePrices);
    }

    @Recurring(id = "fetchStockPrices", cron = "*/30 * * * * *") // Every 30 seconds
    @Job(name = "Fetch and store stock prices")
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
        return repository.findTop20BySymbolOrderByTimestampDesc(symbol);
    }
}
