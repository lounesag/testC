package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.exception.CustomException;
import com.model.Person;
import com.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value ="/listPersons", method = RequestMethod.GET)
	public @ResponseBody Map listPersons() {
		Map<String, Object> map = new HashMap();
		map.put("personList", personService.listPersons());
		return map;
	}

	 @RequestMapping(value = "/get/{personId}", method = RequestMethod.GET)
     public @ResponseBody Map getPerson(@PathVariable int personId) throws CustomException {
		 	Map<String, Object> map = new HashMap();
            Person person = personService.getPerson(personId);
            map.put("person", person);
            return map;
     }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Map savePerson(@RequestBody Person person) {
    	 Map<String, Object> map = new HashMap();
         personService.savePerson(person);
         map.put("created", "success");
         return map;
     }

     @RequestMapping("/delete/{personId}")
     @ResponseStatus(HttpStatus.OK)
     public @ResponseBody Map deletePerson(@PathVariable("personId") int id) {
         Map<String, Object> map = new HashMap();   
    	 personService.deletePerson(id);
    	 map.put("delete", "success");
         map.put("person", id);
         return map;
     }

	 @RequestMapping(value = "/get/{personName}/{personFirstName}", method = RequestMethod.GET)
     public @ResponseBody Map getPersonByNameAndFirstName(@PathVariable("personName") String personName, @PathVariable("personFirstName") String personFirstName) throws CustomException {
		 	Map<String, Object> map = new HashMap();
            Person person;
			try {
				person = personService.getPersonByNameAndFirstName(personName, personFirstName);
	            map.put("person", person);

			} catch (CustomException e) {
				throw new CustomException("Exception: ",e);
			}
            return map;
     }

	 @RequestMapping(value = "/update", method = RequestMethod.PUT)	 
	 public @ResponseBody Map updatePerson(@RequestBody Person personNewAttrib ) throws CustomException {
		 	Map<String, Object> map = new HashMap();
		 	Person person;
		 	try {
	            person = personService.getPerson(personNewAttrib.getId());

	            person.setAdress(personNewAttrib.getAdress());
	            person.setBirthday(personNewAttrib.getBirthday());
	            person.setCountry(personNewAttrib.getCountry());
	            person.setEmail(person.getEmail());
	            person.setFirstname(personNewAttrib.getFirstname());
	            person.setGender(person.getGender());
	            person.setName(person.getName());
	            person.setPhone(personNewAttrib.getPhone());
	            person.setPseudo(personNewAttrib.getPseudo());

	            personService.savePerson(person);

	            map.put("update", "success");

			} catch (CustomException e) {
				throw new CustomException("Exception: ",e);
			}
            return map;
	 }
     
}
