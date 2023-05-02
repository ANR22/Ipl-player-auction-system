package com.websocket.auction;

public class BidMessage {
	private Long teamId;
	private Long playerId;
	private String teamName;
	
	public BidMessage(Long teamId, Long playerId, String teamName) {
		super();
		this.teamId = teamId;
		this.playerId = playerId;
		this.teamName = teamName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
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
	
	
}
