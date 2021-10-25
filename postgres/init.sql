CREATE TABLE airport_data (
	airportAppId BIGSERIAL PRIMARY KEY not null,
	departureTime VARCHAR ( 50 ) NOT NULL,
	destination  VARCHAR ( 50 ) NOT NULL,
	destinationAirportIATA VARCHAR ( 255 ) NOT NULL,
	flightNo  VARCHAR ( 255 ) NOT NULL,
        sunday  VARCHAR ( 10 ),
      monday  VARCHAR ( 10 ),
     tuesday  VARCHAR ( 10 ),
    wedday  VARCHAR ( 10 ),
   thursday  VARCHAR ( 10 ),
   friday  VARCHAR ( 10 ),
  saturday VARCHAR ( 10 )

);