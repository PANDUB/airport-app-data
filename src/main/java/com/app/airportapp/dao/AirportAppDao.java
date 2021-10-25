package com.app.airportapp.dao;

import com.app.airportapp.entity.AirPortData;
import com.app.airportapp.entity.AirPortDataResp;

import java.util.List;

public interface AirportAppDao {

    public void insert(AirPortData airPortData);
    public void update(AirPortData airPortData);
    public void delete(AirPortData airPortData);
    public List<AirPortDataResp> getAll();
}
