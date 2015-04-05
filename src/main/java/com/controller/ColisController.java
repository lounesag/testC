package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.PersonDao;
import com.exception.CustomException;
import com.model.Colis;
import com.model.Person;
import com.service.ColisService;
import com.service.PersonService;
import com.system.authentication.AuthenticationService;
import com.system.authentication.TokenManager;

@RestController
@RequestMapping("/colis")
public class ColisController {

	@Autowired
	private ColisService colisService;
	
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private PersonService personService;

	@Autowired
	private PersonDao personDao;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value ="/listColis", method = RequestMethod.GET)
	public @ResponseBody Map listColis() {
		Map<String, Object> map = new HashMap();
		map.put("colisList", colisService.listColis());
		return map;
	}

	@PreAuthorize("hasAnyRole('ADMIN','ROLE_SPECIAL')")
	@RequestMapping(value = "/secure/save", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createColis(@RequestBody Colis colis1) throws CustomException {
		Map<String, Object> map = new HashMap<String, Object>();
		UserDetails currentUser = authenticationService.currentUser();

		colisService.setColisService(currentUser, colis1);

		map.put("created", "success");
		return map;
	}

	@PreAuthorize("hasAnyRole('ADMIN','ROLE_SPECIAL')")
	@RequestMapping(value = "/secure/get/{colisId}", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getColisId(@PathVariable("colisId") int colisId) throws CustomException {
		Map<String, Object> map = new HashMap<String,Object>();

		UserDetails currentUser = authenticationService.currentUser();
		
		System.out.println("user current : "+currentUser);		
		Person person = personService.getByLogin(currentUser.getUsername());

		Colis colis = colisService.getColis(colisId);


		System.out.println("person id : "+person.getId() +" colis id :"+colis.getCreatedBy());
		
		
		map.put("colis", colis);
		return map;
	}

}
