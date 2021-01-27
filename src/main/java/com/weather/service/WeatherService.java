package com.weather.service;

import com.weather.model.Daily;
import com.weather.model.DailyWeatherResponse;
import com.weather.model.Period;
import com.weather.model.Root;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private static String WEATHER_URL = "https://api.weather.gov/gridpoints/MLB/33,70/forecast";
    private static String MORNING = "This Morning";
    private static String AFTERNOON = "This Afternoon";
    private static String TONIGHT = "Tonight";

    public double convertToCelsius(int fahrenheit) {

        if(fahrenheit - 32 == 0) {
            return 0.0;
        }

        double rawCelsius = (fahrenheit - 32) / 1.8;

        return Math.floor(rawCelsius * 10) / 10;
    }

    public Root getRootEntity() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Root> response = restTemplate.getForEntity(WEATHER_URL, Root.class);

        return response.getBody();
    }

    public DailyWeatherResponse getDailyWeather() {
        Root root = getRootEntity();
        List<Period> periodList = root.getProperties().getPeriods().stream().filter(Objects::nonNull)
                .filter(period -> period.getName().equals(MORNING) ||
                        period.getName().equals(AFTERNOON) ||
                        period.getName().equals(TONIGHT))
                .collect(Collectors.toList());

        int tempFahrenheit = periodList.stream().mapToInt(period -> period.getTemperature()).max().getAsInt();

        String dayName = LocalDate.now().getDayOfWeek().name();

        Daily daily = new Daily(
                dayName.substring(0, 1) + dayName.substring(1).toLowerCase(),
                convertToCelsius(tempFahrenheit),
                periodList.stream().findFirst().get().getShortForecast());

        DailyWeatherResponse dailyWeatherResponse = new DailyWeatherResponse();

        dailyWeatherResponse.getDailyList().add(daily);

        return dailyWeatherResponse;
    }


}
