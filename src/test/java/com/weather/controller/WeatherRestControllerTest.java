package com.weather.controller;

import com.weather.model.Daily;
import com.weather.model.DailyWeatherResponse;
import com.weather.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("qa")
@WebMvcTest(WeatherRestController.class)
@AutoConfigureMockMvc
class WeatherRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    private static String WEATHER_URL = "/weather/v1/current/day";

    @BeforeEach
    public void setup() {
        Daily daily = new Daily("Monday", 26.6, "Cloudy");
        DailyWeatherResponse dailyWeatherResponse = new DailyWeatherResponse();
        dailyWeatherResponse.getDailyList().add(daily);

        when(weatherService.getDailyWeather()).thenReturn(dailyWeatherResponse);
    }

    @Test
    public void getWeatherSuccessfulTest() throws Exception {
        mockMvc.perform(get(WEATHER_URL))
                .andExpect(status().is2xxSuccessful());
    }

}