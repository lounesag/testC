package com.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.exception.CustomException;
import com.model.Colis;

public interface ColisService {

	 /*
     * CREATE and UPDATE
     */
    public void saveColis(Colis colis); // create and update

    /*
     * READ
     */
    public List<Colis> listColis();

    public Colis getColis(int id);

    /*
     * DELETE
     */
    public void deleteColis(int id);
   
    public void setColisService(UserDetails currentUser, Colis colis ) throws CustomException;

}
