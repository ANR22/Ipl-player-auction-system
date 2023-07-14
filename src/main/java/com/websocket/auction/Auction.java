package com.websocket.auction;

import java.beans.JavaBean;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.websocket.player.Player;
import com.websocket.player.PlayerService;
import com.websocket.team.TeamService;

//class AuctionDetails{
//	boolean started;
//	CurrentBid currentBid;
//	Player player;
//	public AuctionDetails() {
//		super();
//	}
//	public AuctionDetails(boolean started, CurrentBid currentBid, Player player) {
//		super();
//		this.started = started;
//		this.currentBid = currentBid;
//		this.player = player;
//	}
//	public boolean getStarted() {
//		return started;
//	}
//	public void setStarted(Boolean started) {
//		this.started = started;
//	}
//	public CurrentBid getCurrentBid() {
//		return currentBid;
//	}
//	public void setCurrentBid(CurrentBid currentBid) {
//		this.currentBid = currentBid;
//	}
//	public Player getPlayer() {
//		return player;
//	}
//	public void setPlayer(Player player) {
//		this.player = player;
//	}
//	
//}
//

@Configuration
@JavaBean
public class Auction {
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private CurrentBid currentBid;
	
	@Autowired
	private TeamService teamService;
	
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
	
	public boolean isStarted() {
		return started;
	}



	public void setStarted(boolean started) {
		this.started = started;
	}



	public String placeBid() {
		
		SoldPlayer sold = new SoldPlayer(currentBid); 
		
		ds.queryDatabase(sold);
		if(sold.getTeamId() != null) {
			playerService.updatePlayerStatus(sold.getPlayerId());
		}
		return sold.getTeamId() + "" + sold.getTeamId();
		
	}
	
	public String getAuctionDetails() {
		String player = "null";
		String teamName = "null";
		if (currentBid.getPlayerId() != null) {
			player = playerService.getPlayerById(currentBid.getPlayerId()).getString()+"}";
		}
		if (currentBid.getTeamId() != null) {
			teamName = teamService.getTeamById(currentBid.getTeamId()).getName();
		}
		String res = "{\"started\":"+started+",\"currentBid\":"+currentBid.getString()+",\"teamName\":\""+teamName+"\"},\"player\":"+player+"}";
		System.out.println(res);
		return res;
	}
}
