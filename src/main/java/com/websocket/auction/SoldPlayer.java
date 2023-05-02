package com.websocket.auction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SoldPlayer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playerId;
	
	@Column(name="teamId", nullable=false)
	private Long teamId;

	@Column(name="bidAmount", nullable=false)
	private double bidAmount;

	public Long getPlayerId() {
		return playerId;
	}

	public SoldPlayer(CurrentBid currentBid) {
		this.playerId = currentBid.getPlayerId();
		this.teamId = currentBid.getTeamId();
		this.bidAmount = currentBid.getCurrentBidAmount();
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}


	public double getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

	public SoldPlayer(Long playerId, Long teamId, double bidAmount) {
		super();
		this.playerId = playerId;
		this.teamId = teamId;
		this.bidAmount = bidAmount;
	}

	public SoldPlayer() {
		super();
	}
	
}
