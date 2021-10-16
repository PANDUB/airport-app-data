package com.app.airportapp;

import com.app.airportapp.controller.AppDataController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class AirportAppDataApplicationTests {
	@Autowired
	private AppDataController appDataController;
	@Test
	void contextLoads() {
		Assert.assertNotNull(appDataController);
	}

}
