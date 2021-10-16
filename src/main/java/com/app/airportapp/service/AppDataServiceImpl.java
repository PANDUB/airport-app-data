package com.app.airportapp.service;


import com.app.airportapp.entity.AirPortData;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class AppDataServiceImpl implements AppDataService {

    private ConcurrentHashMap<Integer, List<AirPortData>> map;

    public AppDataServiceImpl() {

        map = new ConcurrentHashMap();
    }

    @Override
    public List<AirPortData> getData(LocalDate date)  {

        List<AirPortData> airPortData= map.get(date.getYear());
        if (airPortData == null || airPortData.isEmpty()){
            return  null;
        }
        return  airPortData.stream().filter( x -> x.getDayOfWeekList().contains(date.getDayOfWeek())).
                sorted(Comparator.comparing(x -> x.getDepartureTime())).collect(Collectors.toList());

    }

    @PostConstruct
    public void load() {
        String line = "";
        String splitBy = ",";
        try {
            InputStream inputStream = getClass().getResourceAsStream("/flights.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            List<AirPortData> lst = new ArrayList();

            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] appdata = line.split(splitBy, -1);
                AirPortData airPortData = new AirPortData();
                airPortData.setDepartureTime(appdata[0]);
                airPortData.setDestination(appdata[1]);
                airPortData.setDestinationAirportIATA(appdata[2]);
                airPortData.setFlightNo(appdata[3]);

                if (!"".equals(appdata[4])) {

                    airPortData.getDayOfWeekList().add(DayOfWeek.SUNDAY);

                }
                if (!"".equals(appdata[4])) {
                    airPortData.getDayOfWeekList().add(DayOfWeek.MONDAY);
                }
                if (!"".equals(appdata[5])) {
                    airPortData.getDayOfWeekList().add(DayOfWeek.TUESDAY);
                }
                if (!"".equals(appdata[6])) {
                    airPortData.getDayOfWeekList().add(DayOfWeek.WEDNESDAY);
                }
                if (!"".equals(appdata[7])) {
                    airPortData.getDayOfWeekList().add(DayOfWeek.THURSDAY);
                }
                if (!"".equals(appdata[8])) {
                    airPortData.getDayOfWeekList().add(DayOfWeek.FRIDAY);
                }
                if (!"".equals(appdata[9])) {
                    airPortData.getDayOfWeekList().add(DayOfWeek.SATURDAY);
                }

                lst.add(airPortData);

            }
            lst.remove(0);

            map.put(LocalDate.now().getYear(), lst);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
