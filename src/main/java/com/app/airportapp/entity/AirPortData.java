package com.app.airportapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AirPortData {
    public AirPortData() {

    }
    @JsonProperty("airportAppId")
    private int airportAppId;
    @JsonProperty("departureTime")
    private String departureTime;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("destinationAirportIATA")
    private String destinationAirportIATA;
    @JsonProperty("flightNo")
    private String flightNo;
    @JsonProperty("sunday")
    private String sunday;
    @JsonProperty("monday")
    private String monday;
    @JsonProperty("tuesday")
    private String tuesday;
    @JsonProperty("wedday")
    private String wedday;
    @JsonProperty("thursday")
    private String thursday;
    @JsonProperty("friday")
    private String friday;
    @JsonProperty("saturday")
    private String saturday;


    @JsonIgnore
    private List<DayOfWeek> dayOfWeekList = new ArrayList<>();


}
