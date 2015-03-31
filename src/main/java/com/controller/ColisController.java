package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Colis;
import com.service.ColisService;
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


	@RequestMapping(value ="/listColis", method = RequestMethod.GET)
	public @ResponseBody Map listColis() {
		Map<String, Object> map = new HashMap();
		map.put("colisList", colisService.listColis());
		return map;
	}

	@PreAuthorize("hasAnyRole('ADMIN','ROLE_SPECIAL')")
	@RequestMapping(value = "/secure/save", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createColis(@RequestBody Colis colis1) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserDetails currentUser = authenticationService.currentUser();
		
		System.out.println(currentUser);

		colisService.saveColis(colis1);

		map.put("created", "success");
		return map;
	}
	
	@RequestMapping(value = "/get/{colisId}", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getColisId(@PathVariable("colisId") int colisId) {
		Map<String, Object> map = new HashMap<String,Object>();
		Colis colis = colisService.getColis(colisId);
		map.put("colis", colis);
		return map;
	}
}
