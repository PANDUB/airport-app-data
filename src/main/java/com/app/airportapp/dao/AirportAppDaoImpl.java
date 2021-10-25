package com.app.airportapp.dao;

import com.app.airportapp.entity.AirPortData;
import com.app.airportapp.entity.AirPortDataResp;
import mapper.AirPortDataMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AirportAppDaoImpl  implements  AirportAppDao{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AirportAppDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;

    }

    @Override
    public void insert(AirPortData  airPortData) {
        final String sql = "insert into airport_data(departureTime, destination , destinationAirportIATA,flightNo,sunday,monday,tuesday,wedday,thursday,friday,saturday) " +
                "values(:departureTime,:destination,:destinationAirportIATA,:flightNo,:sunday,:monday,:tuesday,:wedday,:thursday,:friday,:saturday)";
        KeyHolder  keyHolder= new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("departureTime", airPortData.getDepartureTime())
                .addValue("destination", airPortData.getDestination())
                .addValue("destinationAirportIATA", airPortData.getDestinationAirportIATA())
                .addValue("flightNo",airPortData.getFlightNo())
                .addValue("sunday", airPortData.getSunday())
                .addValue("monday", airPortData.getMonday())
                .addValue("tuesday", airPortData.getTuesday())
                .addValue("wedday", airPortData.getWedday())
                .addValue("thursday", airPortData.getThursday())
                .addValue("friday", airPortData.getFriday())
                .addValue("saturday", airPortData.getSaturday());


        namedParameterJdbcTemplate.update(sql,param,keyHolder);

    }

    @Override
    public void update(AirPortData  airPortData) {

    }

    @Override
    public void delete(AirPortData  airPortData) {

    }
    @Override
    public List<AirPortDataResp> getAll(){

        return namedParameterJdbcTemplate.query("select * from airport_data", new AirPortDataMapper());

    }
}
