package com.websocket.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.websocket.auction.Auction;
import com.websocket.auction.CurrentBid;
import com.websocket.player.Player;
import com.websocket.player.PlayerService;



@Controller
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private Auction auc;
	
	@Autowired
	private CurrentBid currentBid;
	
	@Autowired
	private PlayerService playerService;
	
	@GetMapping("/teams")
	public String getAllTeams(Model model) {
		model.addAttribute("teams",teamService.getAllTeams());
		return "teams";
	}
	
	@GetMapping("/teams/new")
	public String getCreateTeamPage(Model model) {
		Team team = new Team();
		model.addAttribute("team",team);
		return "create_team";
	}
	@PostMapping("/teams")
	public String createTeam(@ModelAttribute("team") Team team) {
		teamService.addTeam(team);
		
		return "redirect:teams";
	}
	
	@GetMapping("/teams/{teamId}")
	public String deleteTeam(@PathVariable Long teamId) {
		teamService.deleteTeam(teamId);
		return "redirect:/teams";
	}
	
	@GetMapping("teams/login")
	public String getTeamsLoginPage() {
		return "team_login";
	}
	
	
	@PostMapping("/teams/login")
	public String teamsLogin(@ModelAttribute("team") Team team, Model model) {
		Team getTeam = teamService.getTeamByLoginId(team.getLoginId());
		System.out.println(team.getLoginId());
		System.out.println(getTeam.getLoginId());
		if(getTeam.getPassword().compareTo(team.getPassword()) == 0) {
			System.out.println("success");
			model.addAttribute("team",getTeam);
			if(auc.started) {
				System.out.println("started");
			}
			model.addAttribute("auction_started",auc.started);
			Player player = null;
			Team currTeam = null;
			if(currentBid.getPlayerId() != null) {
				player = playerService.getPlayerById(currentBid.getPlayerId());
				currTeam = teamService.getTeamById(currentBid.getTeamId());
			}
			model.addAttribute("currentBid",currentBid);
			model.addAttribute("player",player);
			model.addAttribute("currTeam",currTeam);
			return "teams_bid";
		}
		else {
			model.addAttribute("error","Invalid credentials");
			return "team_login";
		}
		
	}
	
	
}
