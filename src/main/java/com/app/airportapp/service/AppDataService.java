package com.app.airportapp.service;

import com.app.airportapp.entity.AirPortData;
import com.app.airportapp.entity.AirPortDataResp;

import java.time.LocalDate;
import java.util.List;


public interface AppDataService {

     List<AirPortDataResp> getData(LocalDate date) throws Exception;

    void save(List<AirPortData> airPortData) throws Exception;
}
