package com.dao;
import java.util.List;
import com.exception.CustomException;
import com.model.Colis;
public interface ColisDao {
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

}