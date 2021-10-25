package mapper;

import com.app.airportapp.entity.AirPortData;
import com.app.airportapp.entity.AirPortDataResp;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;


public class AirPortDataMapper implements RowMapper<AirPortDataResp> {


    @Override
    public AirPortDataResp mapRow(ResultSet rs, int rowNum) throws SQLException {
        AirPortDataResp airPortData= new AirPortDataResp();
        airPortData.setDepartureTime(rs.getString("departureTime"));
        airPortData.setDestination(rs.getString("destination"));
        airPortData.setDestinationAirportIATA(rs.getString("destinationAirportIATA"));
        airPortData.setFlightNo(rs.getString("flightNo"));
        if( !"".equals(rs.getString("sunday"))){
            airPortData.getDayOfWeekList().add(DayOfWeek.SUNDAY);
        }
        if( !"".equals(rs.getString("monday"))){
            airPortData.getDayOfWeekList().add(DayOfWeek.MONDAY);
        }
        if( !"".equals(rs.getString("tuesday"))){
            airPortData.getDayOfWeekList().add(DayOfWeek.TUESDAY);
        }
        if( !"".equals(rs.getString("wedday"))){
            airPortData.getDayOfWeekList().add(DayOfWeek.WEDNESDAY);
        }
        if( !"".equals(rs.getString("thursday"))){
            airPortData.getDayOfWeekList().add(DayOfWeek.THURSDAY);
        }
        if( !"".equals(rs.getString("friday"))){
            airPortData.getDayOfWeekList().add(DayOfWeek.FRIDAY);
        }
        if( !"".equals(rs.getString("saturday"))){
            airPortData.getDayOfWeekList().add(DayOfWeek.SATURDAY);
        }

        return airPortData;


    }
}
