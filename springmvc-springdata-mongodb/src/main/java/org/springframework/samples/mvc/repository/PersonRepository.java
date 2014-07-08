package org.springframework.samples.mvc.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.samples.mvc.data.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class PersonRepository {
   private static final Log LOGGER = LogFactory.getLog(PersonRepository.class);

   private MongoTemplate mongoTemplate;

   public PersonRepository() {
   }

   @Autowired
   public PersonRepository(MongoTemplate mongoTemplate) {
      this.mongoTemplate = mongoTemplate;

      if (!mongoTemplate.collectionExists(Person.class)) {
         LOGGER.debug("Creating the Person collection");

         mongoTemplate.createCollection(Person.class);
      }
   }

   public void save(Person person) {
      LOGGER.debug("Saving the following Person object: " + person);

      mongoTemplate.save(person);
   }

   public Person findByEmail(String email) {
      LOGGER.debug("Finding an Person by this email: " + email);

      return mongoTemplate.findOne(
         query(where("email").is(email)),
         Person.class);
   }

   public List<Person> findAll() {
      return mongoTemplate.findAll(Person.class);
   }

   public void removeAll() {
      LOGGER.debug("Removing all Persons");

      mongoTemplate.remove(new Query(), Person.class);
   }
}
