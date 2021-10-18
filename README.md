# Virgin Atlantic ~ Flight Information Display
#Tools 
1. Intellij IDEA
2.Docker
3.Postman
# How to Start the  APP
  `docker-compose up `

# How to stop the  APP
`docker-compose down `

-----
I have tested the app using Postman or curl
#End point to Get Airport Data
Method :GET 
URL :http://localhost:8080/fetchAppData?from=2021-10-10

Curl :

curl --location --request GET 'http://localhost:8080/fetchAppData?from=2021-10-10'

#Application health and metrics
Method :GET
URL :http://localhost:8080/actuator/health
(OR)
curl --location --request GET 'http://localhost:8080/actuator/health'

Method :GET
URL :http://localhost:8080/actuator/metrics
(OR)
curl --location --request GET 'http://localhost:8080/actuator/metrics'


Note: I have added Jenkins pipeline script as well its under progress. 




