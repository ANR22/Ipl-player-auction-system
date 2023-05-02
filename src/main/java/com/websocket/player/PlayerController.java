package com.websocket.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@GetMapping("/players")
	public String getAllPlayers(Model model){
		model.addAttribute("players",playerService.getAllPlayers());
		return "players";
	}
	
	@RequestMapping("/players/new")
	public String createPlayersForm(Model model){
		Player player = new Player();
		model.addAttribute("player",player);
		return "create_player";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/players")
	public String addPlayer(@ModelAttribute("player") Player player) {
		playerService.addPlayer(player);
		return "redirect:/players";
	}
	
	@RequestMapping("/players/edit/{id}")
	public String editPlaylistForm(@PathVariable Long id, Model model) {
		model.addAttribute("player",playerService.getPlayerById(id));
		return "edit_player";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/players/{id}")
	public String updatePlaylist(@PathVariable Long id,
			@ModelAttribute("player") Player playlist,
			Model model) {
		
		// get student from database by id
		Player existingPlayer = playerService.getPlayerById(id);
		existingPlayer.setId(id);
		existingPlayer.setName(playlist.getName());
		
		// save updated student object
		playerService.updatePlayer(existingPlayer);
		return "redirect:/players";		
	}
	
	@RequestMapping("/players/{playerId}")
	public String updatePlaylist(@PathVariable Long playerId) {
		playerService.deletePlaylistById(playerId);
		return "redirect:/players";
	}
}
