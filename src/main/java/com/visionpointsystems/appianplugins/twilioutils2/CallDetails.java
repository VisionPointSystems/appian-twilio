package com.visionpointsystems.appianplugins.twilioutils2;

import java.sql.Timestamp;

public class CallDetails {
	
	private Timestamp startTime;
	private Timestamp endTime;
	private String status;
	private Long duration;
	
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	

}
