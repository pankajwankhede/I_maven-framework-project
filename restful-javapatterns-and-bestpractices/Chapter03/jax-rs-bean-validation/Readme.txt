This is a very basic sample which shows Beanvalidation and JAX-RS

Download Glassfish 4.0

Start Glassfish
cd glassfish4/glassfish/bin
./asadmin start-domain

deploy jax-rs-beanvalidation.war
./asadmin deploy <folder of sample>/target/jax-rs-beanvalidation.war

You can try by using Advanced REST client or PostMan and sending data in the POST method  which has an invalid size called "Smallsize" instead of "Small"
{"type":"Brewed",
 "size":"Smallsize",
 "name":"Americano",
 "price":"3:50"
}
Alternatively using curl to the URL http://localhost:8080/jax-rs-bean-validation/v1/coffees
curl -X POST -d @test.json http://localhost:8080/jax-rs-bean-validation/v1/coffees --header "Content-Type: application/json"

Error in this case is
Value specified is not valid (path = Coffee.addCoffee.arg0.size, invalidValue = SmallSize)

