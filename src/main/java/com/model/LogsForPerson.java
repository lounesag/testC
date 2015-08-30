package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LogsForPerson")
public class LogsForPerson {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String request;

	private String response;

	@Column(name = "server_ip", length = 128)
	private String serverIp;

	@Column(name = "server_name", length = 128)
	private String serverName;

	private Long duration;

	@Column(name = "is_exception", nullable = false)
	private boolean exception;

//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false, nullable = false)
	private Date createdAt;

	public LogsForPerson() {
		this.createdAt = new Date();
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Long getDuration() {
		return this.duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public boolean isException() {
		return this.exception;
	}

	public void setException(boolean exception) {
		this.exception = exception;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getId() {
		return this.id;
	}
}
