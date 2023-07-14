package com.websocket.auction;

import java.beans.JavaBean;

import org.springframework.context.annotation.Configuration;

@Configuration
@JavaBean
public class CurrentBid {
	
	
	private Long teamId;
	private Long playerId;
	
	private double currentBidAmount;
	
	
	public CurrentBid() {
		super();
	}
	
	
	public CurrentBid(Long teamId, Long playerId, double currentBidAmount) {
		super();
		this.teamId = teamId;
		this.playerId = playerId;
		this.currentBidAmount = currentBidAmount;
	}

	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public double getCurrentBidAmount() {
		return currentBidAmount;
	}
	public void setCurrentBidAmount(double d) {
		this.currentBidAmount = d;
	}
	
	public String bidMessage(String teamName) {
		return "{\"teamId\":"+teamId+",\"teamName\":\""+teamName+"\",\"playerId\":"+playerId+",\"bidAmount\":"+currentBidAmount;
	}
	
	public String getString() {
		return "{\"teamId\":"+teamId+",\"playerId\":"+playerId+",\"bidAmount\":"+currentBidAmount;
	}
	
}
