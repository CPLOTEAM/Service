package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Claim {
	
	private int claimid;
	private String claimtype;
	private String claimStatus;
	
	public int getClaimid() {
		return claimid;
	}
	public void setClaimid(int claimid) {
		this.claimid = claimid;
	}
	public String getClaimtype() {
		return claimtype;
	}
	public void setClaimtype(String claimtype) {
		this.claimtype = claimtype;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	

}
