package com.shoppingcart2.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StatisticsResponse {
    @JsonProperty(index = 2)
    private Map<String, Integer> statistics;
    @JsonProperty(index = 3)
    private String message;
    @JsonProperty(index = 1)
    private Integer numberOfSales;

    public StatisticsResponse(Integer numberOfSales, Map<String,Integer> statistics, String message) {
        this.numberOfSales = numberOfSales;
        this.statistics = statistics;
        this.message = message;      
    }

    public Integer getNumberOfSales() {
        return this.numberOfSales;
    }

    public void setNumberOfSales(Integer numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public Map<String,Integer> getStatistics() {
        return this.statistics;
    }

    public void setStatistics(Map<String,Integer> statistics) {
        this.statistics = statistics;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
