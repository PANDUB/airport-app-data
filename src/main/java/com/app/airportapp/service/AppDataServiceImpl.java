package com.app.airportapp.service;


import com.app.airportapp.controller.AppDataController;
import com.app.airportapp.dao.AirportAppDao;
import com.app.airportapp.entity.AirPortData;
import com.app.airportapp.entity.AirPortDataResp;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class AppDataServiceImpl implements AppDataService {
    Logger logger = LoggerFactory.getLogger(AppDataController.class);
    private ConcurrentHashMap<Integer, List<AirPortDataResp>> map;
    private  List<AirPortData> lst = new ArrayList();
    private MeterRegistry meterRegistry;
    private AirportAppDao airportAppDao;

    public AppDataServiceImpl(MeterRegistry meterRegistry, AirportAppDao  airportAppDao) {
        this.map = new ConcurrentHashMap();
       this.meterRegistry= meterRegistry;
       this.airportAppDao=airportAppDao;

    }

    @Override
    public List<AirPortDataResp> getData(LocalDate date)  {
       List<AirPortDataResp>   airPortData = map.get (date.getYear());
        if (airPortData == null || airPortData.isEmpty() ) {
            List<AirPortDataResp> lst = airportAppDao.getAll();
            if(lst.isEmpty()){
                return null;
            }
            loadMap(lst);
            airPortData  =map.get (date.getYear());

        }
        return  airPortData.stream().filter( x -> x.getDayOfWeekList().contains(date.getDayOfWeek())).
                sorted(Comparator.comparing(x -> x.getDepartureTime())).collect(Collectors.toList());

    }

    @Override
    public void save(List<AirPortData> airPortData){

        airPortData.stream().forEach(x ->airportAppDao.insert(x));

    }

    @PostConstruct
    public void load() {
        String line = "";
        String splitBy = ",";
        try {
            InputStream inputStream = getClass().getResourceAsStream("/flights.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] appdata = line.split(splitBy, -1);
                AirPortData airPortData = new AirPortData();
                airPortData.setDepartureTime(appdata[0]);
                airPortData.setDestination(appdata[1]);
                airPortData.setDestinationAirportIATA(appdata[2]);
                airPortData.setFlightNo(appdata[3]);
                if (!"".equals(appdata[4])) {

                    airPortData.setSunday(appdata[4]);
                }
                if (!"".equals(appdata[5])) {
                    airPortData.setMonday(appdata[5]);
                }
                if (!"".equals(appdata[6])) {
                    airPortData.setTuesday(appdata[6]);
                }
                if (!"".equals(appdata[7])) {
                    airPortData.setWedday(appdata[7]);
                }
                if (!"".equals(appdata[8])) {
                    airPortData.setThursday(appdata[8]);
                }
                if (!"".equals(appdata[9])) {
                    airPortData.setFriday(appdata[9]);
                }
                if (!"".equals(appdata[10])) {
                    airPortData.setSunday(appdata[10]);
                }
                lst.add(airPortData);
            }
            lst.remove(0);
            save(lst);

        } catch (IOException e) {
            logger.error("exception to read flight data", e);
        }

    }

    public void loadMap(List<AirPortDataResp> airPortDataList) {

        map.put(LocalDate.now().getYear(), airPortDataList);


    }
}
