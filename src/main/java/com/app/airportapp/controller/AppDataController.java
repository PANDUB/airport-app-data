package com.app.airportapp.controller;

import com.app.airportapp.entity.AirPortData;
import com.app.airportapp.exception.RecordNotFoundException;
import com.app.airportapp.service.AppDataService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class AppDataController {
    Logger logger = LoggerFactory.getLogger(AppDataController.class);
    private  AppDataService appDataService;


    @Autowired
    public AppDataController(AppDataService appDataService) {
        this.appDataService = appDataService;


    }

    @GetMapping("/fetchAppData")
    public ResponseEntity<List<AirPortData>> fetchAppData(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) throws Exception {
       // logger.info("Fetch Airport application data");
        List<AirPortData>  airPortDataList= appDataService.getData(date);
          if (airPortDataList == null){
              throw new RecordNotFoundException("No record Present");
          }
          return new ResponseEntity<>(airPortDataList, HttpStatus.OK);


    }

}
