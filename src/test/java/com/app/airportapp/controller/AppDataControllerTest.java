package com.app.airportapp.controller;

import com.app.airportapp.entity.AirPortData;
import com.app.airportapp.entity.AirPortDataResp;
import com.app.airportapp.exception.RecordNotFoundException;
import com.app.airportapp.service.AppDataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppDataControllerTest {

    @Mock
    AppDataService appDataService;
    AppDataController appDataController;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
       appDataController= new AppDataController(appDataService);
    }


    @Test(expected = RecordNotFoundException.class)
    public void testControllerThrowException() throws Exception {
        Mockito.when(appDataService.getData(LocalDate.now())).thenReturn(null);
         appDataController.fetchAppData(LocalDate.now());
    }
    @Test
    public void testControllerWithDataReturn() throws Exception {
        List<AirPortDataResp>  lst = new ArrayList<>();
        lst.add(testData());
        Mockito.when(appDataService.getData(LocalDate.now())).thenReturn(lst);
        ResponseEntity<List<AirPortDataResp>> result = appDataController.fetchAppData(LocalDate.now());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getBody().get(0).getFlightNo(),"1234");
        Assert.assertEquals(result.getBody().get(0).getDestination(),"Hyd");
        Assert.assertEquals(result.getBody().get(0).getDestinationAirportIATA(),"RGV");
        Assert.assertEquals(result.getBody().get(0).getDepartureTime(),"09:00");

    }

    private AirPortDataResp testData(){
        AirPortDataResp a =new AirPortDataResp();
        a.setDepartureTime("09:00");
        a.setDestinationAirportIATA("RGV");
        a.setDestination("Hyd");
        a.setFlightNo("1234");
        return a;
    }


}
