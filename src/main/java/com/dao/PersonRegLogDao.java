package com.dao;

import java.util.List;

import com.model.LogsForPerson;

public interface PersonRegLogDao {
	/* create and update */
    public void savePersonRegLog(LogsForPerson personRegLog);

    /*
     * READ
     */
    public List<LogsForPerson> listLogsForPersons();
    public LogsForPerson getLogsForPerson(int id);

    /*
     * DELETE
     */
    public void deleteLogsForPerson(int id);
}
