package com.app.airportapp.service;

import com.app.airportapp.dao.AirportAppDao;
import com.app.airportapp.entity.AirPortData;
import com.app.airportapp.entity.AirPortDataResp;
import io.micrometer.core.instrument.MeterRegistry;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppDataServiceImplTest {
    AppDataServiceImpl appDataService;
    @Mock
    private MeterRegistry meterRegistry;
    @Mock
    private AirportAppDao airportAppDao;
    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        appDataService= new AppDataServiceImpl(meterRegistry,airportAppDao);
    }
    @Test
    public void testLoadDataReturnData(){

        Mockito.when(airportAppDao.getAll()).thenReturn(testData());
        appDataService.load();
        LocalDate date = LocalDate.of(2021, 1, 10);
        List<AirPortDataResp> lst = appDataService.getData(date);
        Assert.assertNotNull(lst);
        Assert.assertEquals(1,lst.size());

    }
    @Test
    public void testLoadReturnNull(){
        appDataService.load();
        LocalDate date = LocalDate.of(2022, 1, 8);
        List<AirPortDataResp> lst = appDataService.getData(date);
        Assert.assertNull(lst);


    }
    private List<AirPortDataResp> testData(){
        AirPortDataResp a =new AirPortDataResp();
        a.setDepartureTime("09:00");
        a.setDestinationAirportIATA("RGV");
        a.setDestination("Hyd");
        a.setFlightNo("1234");
        a.getDayOfWeekList().add(DayOfWeek.SUNDAY);
        a.getDayOfWeekList().add(DayOfWeek.MONDAY);
        a.getDayOfWeekList().add(DayOfWeek.TUESDAY);
        a.getDayOfWeekList().add(DayOfWeek.WEDNESDAY);
        List lst = new ArrayList();
        lst.add(a);
        return lst;
    }


}
