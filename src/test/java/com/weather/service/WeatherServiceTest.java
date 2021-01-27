package com.weather.service;

import com.weather.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class WeatherServiceTest {

    private WeatherService weatherService;

    @BeforeEach
    public void setup() {
        weatherService = spy(new WeatherService());

        Root root = new Root();
        root.setProperties(new Properties());

        root.getProperties().setPeriods(new ArrayList<>());
        Period period = new Period();
        period.setShortForecast("Hot");
        period.setTemperature(80);
        period.setName("This Afternoon");
        root.getProperties().getPeriods().add(period);

        doReturn(root).when(weatherService).getRootEntity();

    }

    @Test
    public void convertToCelsiusTest() {
        double actual = weatherService.convertToCelsius(80);

        assertEquals(26.6, actual);
    }

    @Test
    public void convertToCelsiusZeroTest() {
        double actual = weatherService.convertToCelsius(32);

        assertEquals(0.0, actual);
    }

    @Test
    public void convertToCelsiusBelowZeroTest() {
        double actual = weatherService.convertToCelsius(16);

        assertEquals(-8.9, actual);
    }

    @Test
    public void getRootEntityTest() {
        Root root = weatherService.getRootEntity();

        assertFalse(root.getProperties().getPeriods().isEmpty());
    }

    @Test
    public void getDailyWeatherTest() {
        DailyWeatherResponse response = weatherService.getDailyWeather();

        assertEquals(1, response.getDailyList().size());

        Daily daily = response.getDailyList().get(0);

        String dayName = LocalDate.now().getDayOfWeek().name();

        dayName = dayName.substring(0, 1) + dayName.substring(1).toLowerCase();

        assertEquals(dayName, daily.getDayName());
        assertEquals(26.6, daily.getTempHighCelsius());
        assertEquals("Hot", daily.getForecastBlurp());
    }

}