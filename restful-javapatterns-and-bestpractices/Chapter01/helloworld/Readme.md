This is a very basic sample which shows helloworld JAX-RS sample with client API

Download Glassfish 4.0

Start Glassfish
cd glassfish4/glassfish/bin
./asadmin start-domain

Build the sample by running
mvn clean install
cd target
deploy helloworld.war
./asadmin deploy <folder of sample>/target/helloworld.war

Use Postman or call curl http://localhost:8080/helloworld/TestClient


