package com.epcs.bean;
import java.io.Serializable;



public class PackContent implements Serializable{
	private static final long serialVersionUID  = 1L;
    private String id;
    private String activationdate;
    private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActivationdate() {
		return activationdate;
	}
	public void setActivationdate(String activationdate) {
		this.activationdate = activationdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
	
	
	
}
