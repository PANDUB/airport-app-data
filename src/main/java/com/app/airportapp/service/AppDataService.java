package com.app.airportapp.service;

import com.app.airportapp.entity.AirPortData;

import java.time.LocalDate;
import java.util.List;


public interface AppDataService {

     List<AirPortData> getData(LocalDate date) throws Exception;
}
