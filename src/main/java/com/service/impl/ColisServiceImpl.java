package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ColisDao;
import com.model.Colis;
import com.service.ColisService;

@Service
public class ColisServiceImpl implements ColisService{

	@Autowired
	private ColisDao colisDao;

	@Transactional
	public void saveColis(Colis colis) {
		colisDao.saveColis(colis);
	}

	@Transactional
	public List<Colis> listColis() {
		return colisDao.listColis();
	}

	@Transactional
	public Colis getColis(int id) {
		return colisDao.getColis(id);
	}

	@Transactional
	public void deleteColis(int id) {
		colisDao.deleteColis(id);
	}

}