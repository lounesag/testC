package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PersonRegLogDao;
import com.exception.CustomException;
import com.model.LogsForPerson;
import com.service.PersonRegLogService;

@Service
public class PersonRegLogServiceImpl implements PersonRegLogService {

	@Autowired
	private PersonRegLogDao personRegLogDao;
	
	@Transactional
	public void savePersonRegLog(LogsForPerson personRegLog) throws CustomException {
					personRegLogDao.savePersonRegLog(personRegLog);			
		}

	@Override
	public List<LogsForPerson> listLogsForPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogsForPerson getLogsForPerson(int id) throws CustomException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLogsForPerson(int id) {
		// TODO Auto-generated method stub
		
	}

}