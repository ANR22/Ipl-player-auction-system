package com.websocket.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import com.websocket.auction.Auction;
import com.websocket.auction.CurrentBid;
import com.websocket.player.Player;
import com.websocket.player.PlayerService;


class TeamLogin{
	String loginId;
	String password;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public TeamLogin(String loginId, String password) {
		super();
		this.loginId = loginId;
		this.password = password;
	}
	public TeamLogin() {
		super();
		
	}
	
}

class LoginResponse{
	Team team;
	CurrentBid currentBid;
	Player player;
	public LoginResponse(Team team, CurrentBid currentBid, Player player) {
		super();
		this.team = team;
		this.currentBid = currentBid;
		this.player = player;
	}
	public LoginResponse() {
		super();
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public CurrentBid getCurrentBid() {
		return currentBid;
	}
	public void setCurrentBid(CurrentBid currentBid) {
		this.currentBid = currentBid;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
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
	public ResponseEntity<Team> teamsLogin(@RequestBody TeamLogin team, Model model) {
		
		Team getTeam = teamService.getTeamByLoginId(team.getLoginId());
		LoginResponse res = new LoginResponse();
		System.out.println(team.getLoginId());
		System.out.println(getTeam.getLoginId());
		if(getTeam.getPassword().compareTo(team.getPassword()) == 0) {
			System.out.println("success");
			model.addAttribute("team",getTeam);
			
			model.addAttribute("auction_started",auc.started);
//			Player player = null;
//			Team currTeam = null;
			
//			res.team = getTeam;
//			if(currentBid.getPlayerId() != null) {
//				player = playerService.getPlayerById(currentBid.getPlayerId());
//				currTeam = teamService.getTeamById(currentBid.getTeamId());
//				res.player = player;
//				res.currentBid = currentBid;
//			}
//			model.addAttribute("currentBid",currentBid);
//			model.addAttribute("player",player);
//			model.addAttribute("currTeam",currTeam);
//			return "teams_bid";
//			String response = "{team_name:"+getTeam.getName()+","+" team_id:"+getTeam.getId()+"team_login_id:"+getTeam.getLoginId()+"}";
			getTeam.setPassword("");
			return ResponseEntity.ok(getTeam);

		}
		else {
			model.addAttribute("error","Invalid credentials");
//			return "team_login";
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(getTeam);
		}
		
	}
	
	
}
