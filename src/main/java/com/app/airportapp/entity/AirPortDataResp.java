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
public class AirPortDataResp {
    public AirPortDataResp() {

    }
    @JsonProperty("departureTime")
    private String departureTime;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("destinationAirportIATA")
    private String destinationAirportIATA;
    @JsonProperty("flightNo")
    private String flightNo;
    @JsonIgnore
    private List<DayOfWeek> dayOfWeekList = new ArrayList<>();


}
