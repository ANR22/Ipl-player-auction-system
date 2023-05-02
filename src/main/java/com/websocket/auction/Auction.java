package com.websocket.auction;

import java.beans.JavaBean;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.websocket.player.Player;
import com.websocket.player.PlayerService;

@Configuration
@JavaBean
public class Auction {
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private CurrentBid currentBid;
	
	private int totalNumberOfPlayers;
	private int currPlayerNumber;
	private List<Player> listOfPlayers;
	public boolean started;
	
	
	@Autowired
	private DatabaseService ds;
	
	public Auction() {
				super();
				started = false;
				

	}



	public int getTotalNumberOfPlayers() {
		return totalNumberOfPlayers;
	}



	public void setTotalNumberOfPlayers(int totalNumberOfPlayers) {
		this.totalNumberOfPlayers = totalNumberOfPlayers;
	}



	public int getCurrPlayerNumber() {
		return currPlayerNumber;
	}



	public void setCurrPlayerNumber(int currPlayerNumber) {
		this.currPlayerNumber = currPlayerNumber;
	}



	public List<Player> getListOfPlayers() {
		return listOfPlayers;
	}



	public void setListOfPlayers() {
		this.listOfPlayers = playerService.getAllPlayers();
	}

	public Player getNextPlayer() {
		while(currPlayerNumber < listOfPlayers.size()) {
			if(listOfPlayers.get(currPlayerNumber).getStatus().equals("unsold")) {
				currentBid.setPlayerId(this.listOfPlayers.get(currPlayerNumber).getId());
				currentBid.setTeamId(null);
				currentBid.setCurrentBidAmount(this.listOfPlayers.get(currPlayerNumber).getBasePrice());
				return this.listOfPlayers.get(currPlayerNumber++);
			}else {
				currPlayerNumber++;
			}
		}
		return new Player();
		
		
		
	}

	
	public String teamBid(Long teamId, Long playerId,String teamName) {
		
		if(currentBid.getTeamId() == null) {
			currentBid.setCurrentBidAmount(currentBid.getCurrentBidAmount());
		}else {
			currentBid.setCurrentBidAmount(currentBid.getCurrentBidAmount() + 0.5);
		}
		currentBid.setTeamId(teamId);
		currentBid.setPlayerId(playerId);
		return currentBid.bidMessage(teamName)+",\"type\":\"bid\"}";
	}
	
	public void startAuction() {
		setListOfPlayers();
		this.currPlayerNumber = 0;
		this.totalNumberOfPlayers = this.listOfPlayers.size();
		this.started = true;
		
	}
	
	public String placeBid() {
		
		SoldPlayer sold = new SoldPlayer(currentBid); 
		
		ds.queryDatabase(sold);
		if(sold.getTeamId() != null) {
			playerService.updatePlayerStatus(sold.getPlayerId());
		}
		return sold.getTeamId() + "" + sold.getTeamId();
		
	}
}
