package com.app.airportapp.controller;

import com.app.airportapp.entity.AirPortData;
import com.app.airportapp.exception.RecordNotFoundException;
import com.app.airportapp.service.AppDataService;
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
    AppDataService appDataService;

    @Autowired
    public AppDataController(AppDataService appDataService) {
        this.appDataService = appDataService;

    }

    @GetMapping("/fetchAppData")
    public ResponseEntity<List<AirPortData>> fetchAppData(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) throws Exception {

        List<AirPortData>  airPortDataList= appDataService.getData(date);
          if (airPortDataList == null){
              throw new RecordNotFoundException("No record Present");
          }
          return new ResponseEntity<>(airPortDataList, HttpStatus.OK);


    }

}
