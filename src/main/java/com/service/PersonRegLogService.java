package com.service;

import java.util.List;

import com.exception.CustomException;
import com.model.LogsForPerson;

public interface PersonRegLogService {
	/*
	 * CREATE and UPDATE
	 */
	public void savePersonRegLog(LogsForPerson personRegLog) throws Exception;

	/*
		 * READ
		 */
		public List<LogsForPerson> listLogsForPersons();
		public LogsForPerson getLogsForPerson(int id) throws CustomException;
	
		/*
		 * DELETE
		 */
		public void deleteLogsForPerson(int id);

	
}
