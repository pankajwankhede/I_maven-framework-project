package org.springframework.samples.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.mvc.data.Person;
import org.springframework.samples.mvc.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PersonController {

   @Autowired
   private PersonRepository personRepository;

   @RequestMapping(value = "/person", method = POST)
   public @ResponseBody String save(@Valid Person person, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
         return bindingResult.getAllErrors().toString();
      }
      personRepository.save(person);

      return "Person added!";
   }

   @RequestMapping(value = "/person/delete/all", method = POST)
   public @ResponseBody String removeAll() {
      personRepository.removeAll();

      return "All Persons removed!";
   }

   @RequestMapping(value = "/person/list")
   public @ResponseBody String findAll() {
      List<Person> personList = personRepository.findAll();

      return personList.toString();
   }

   @RequestMapping("/person")
   public @ResponseBody String findByEmail(@RequestParam String email) {
      if ((email == null) || (email.isEmpty())) {
         return "Unable to find an Person without email value";
      }
      Person person = personRepository.findByEmail(email);

      if (null == person) {
         return "Person not found with the following email: " + email;
      }
      return "Found the following person: " + person;
   }
}
