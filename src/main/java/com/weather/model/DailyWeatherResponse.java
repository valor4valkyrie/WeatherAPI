package com.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DailyWeatherResponse {
    @JsonProperty("daily")
    List<Daily> dailyList = new ArrayList<>();

    public List<Daily> getDailyList() {
        return dailyList;
    }

    public void setDailyList(List<Daily> dailyList) {
        this.dailyList = dailyList;
    }
}
