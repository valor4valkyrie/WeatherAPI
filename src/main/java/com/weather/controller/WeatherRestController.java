package com.weather.controller;

import com.weather.model.DailyWeatherResponse;
import com.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather/v1")
public class WeatherRestController {

    private WeatherService weatherService;

    @Autowired
    public WeatherRestController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/current/day", produces = MediaType.APPLICATION_JSON_VALUE)
    public DailyWeatherResponse getCurrentWeather() {
        return weatherService.getDailyWeather();
    }
}
