package com.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Daily {

    @JsonProperty("day_name")
    private String dayName;

    @JsonProperty("temp_high_celsius")
    private double tempHighCelsius;

    @JsonProperty("forecast_blurp")
    private String forecastBlurp;

    public Daily() {
    }

    public Daily(String dayName, double tempHighCelsius, String forecastBlurp) {
        this.dayName = dayName;
        this.tempHighCelsius = tempHighCelsius;
        this.forecastBlurp = forecastBlurp;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public double getTempHighCelsius() {
        return tempHighCelsius;
    }

    public void setTempHighCelsius(double tempHighCelsius) {
        this.tempHighCelsius = tempHighCelsius;
    }

    public String getForecastBlurp() {
        return forecastBlurp;
    }

    public void setForecastBlurp(String forecastBlurp) {
        this.forecastBlurp = forecastBlurp;
    }
}
