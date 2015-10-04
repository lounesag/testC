package com.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.enumurations.RoleEnum;
import com.exception.CustomException;
import com.model.LogsForPerson;
import com.model.Person;
import com.model.Role;
import com.service.PersonRegLogService;
import com.service.PersonService;
import com.system.authentication.AuthenticationService;
import com.system.authentication.TokenManager;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private PersonRegLogService personRegLogService;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value ="/secure/listPersons", method = RequestMethod.GET)
	public @ResponseBody Map listPersons() {
		UserDetails currentUser = authenticationService.currentUser();
		System.out.println(currentUser);
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
	public @ResponseBody Map savePerson(@RequestBody Person person, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap();
		LogsForPerson personRegLog = new LogsForPerson();
		long duration;
		long startTime = System.currentTimeMillis();
		
		String serverName = request.getLocalName();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		   if (ipAddress == null) {  
			   ipAddress = request.getRemoteAddr();  
			   
		   }
		   
		personRegLog.setServerIp(ipAddress);
		personRegLog.setServerName(serverName);

		String requestUrl = request.getRequestURL().toString();
		//String parameters = request.getParameter("login");
		//requestUrl = requestUrl.concat(parameters.toString());
		//System.out.println("******************"+parameters);

		personRegLog.setRequest(requestUrl);

		try {
			personService.savePerson(person);
			personRegLog.setException(false);
			map.put("created", "success");
			personRegLog.setResponse(map.toString());
		} catch (CustomException e) {
			String response = getStackTrace(e);
			personRegLog.setException(true);
			response = response.substring(0, 1024);
			System.out.println("coucou jsui la"+response.length());

			map.put("created", "Failed");
			map.put("response", response);
			personRegLog.setResponse(map.toString());
		}

	    duration = System.currentTimeMillis() - startTime;
	    personRegLog.setDuration(duration);
	    personRegLogService.savePersonRegLog(personRegLog);

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
	public @ResponseBody Map updatePerson(@RequestBody Person personNewAttrib ) throws Exception {
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
			person.setLogin(personNewAttrib.getLogin());

			personService.savePerson(person);

			map.put("update", "success");

		} catch (CustomException e) {
			throw new CustomException("Exception: ",e);
		}
		return map;
	}


	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/secure/update/authority", method = RequestMethod.POST)	 
	public @ResponseBody Map updatePersonAuthority(@RequestBody Person personNewAuthority) throws Exception {
		Map<String, Object> map = new HashMap();
		UserDetails currentUser = authenticationService.currentUser();
		Person person = personService.getPerson(personNewAuthority.getId());
		
		if (person != null){
			updateRolePerson(person, personNewAuthority);
		} else {
			throw new CustomException("person with id: "+personNewAuthority.getId()+" is not valid");
		}
		
		map.put("update authority", "success");
		return map;
	}

	private void updateRolePerson(Person person, Person personNewAuthority) throws Exception{
		try {
			Role role = person.getRole();
			Role roleNew = personNewAuthority.getRole();
			String roleNewName = roleNew.getRoleName();
			if (role!=null) {
				switch (roleNewName) {
				case "ADMIN":
				case "USER_SIMPLE":
				case "ROLE_SPECIAL":
					person.setRole(roleNew);
					break;

				default:
					throw new CustomException("Authority name invalid : "+roleNewName);
				}
			}
			personService.savePerson(person);

		} catch (CustomException e) {
			throw new CustomException("Exception: ",e);
		}
	}

	public static String getStackTrace(final Throwable throwable) {
	     final StringWriter sw = new StringWriter();
	     final PrintWriter pw = new PrintWriter(sw, true);
	     throwable.printStackTrace(pw);
	     return sw.getBuffer().toString();
	}
	
}
