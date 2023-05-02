package com.websocket.auction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.websocket.player.Player;

@Controller
public class AuctionController {
	
	@Autowired
	Auction auc;
	
	private int timerStopped;
	
	
	@GetMapping("/auction")
	public String getAuctionPage(Model model) {
		timerStopped = 0;
		auc.startAuction();
		Player nextPlayer = auc.getNextPlayer();
		System.out.println(nextPlayer.getName());
		model.addAttribute("player",nextPlayer);
		return "auction_page";
	}
	
	@MessageMapping("/bid")
	@SendTo("/biddings/listen")
	public String bid(BidMessage bidMessage) throws Exception{
//		System.out.println("bidding");
//		System.out.println(bidMessage.getTeamId());
//		System.out.println(bidMessage.getPlayerId());
		timerStopped = 0;
		return auc.teamBid(bidMessage.getTeamId(),bidMessage.getPlayerId(),bidMessage.getTeamName());
		
	}
	
	@MessageMapping("/timerstop")
	@SendTo("/biddings/listen")
	public synchronized String timerStop(TimerStopMessage timerStopMessage) {
		timerStopped += 1;
		if(timerStopped >= 3) {
			System.out.println("bidding stop");
			System.out.println(auc.placeBid());
			Player nextPlayer = auc.getNextPlayer();
			timerStopped = 0;
			if (nextPlayer.getName() == null) {
				return "{\"type\":\"auctionOver\"}";
			}
			return nextPlayer.getString()+",\"type\":\"nextPlayer\"}";
		}
		return "{\"type\":\"timerstop\"}";
	}
	
	
}
