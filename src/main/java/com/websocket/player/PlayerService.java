package com.websocket.player;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class PlayerService {


private PlayerRepository playerRepository;
	

	public PlayerService(PlayerRepository playerRepository) {
		super();
		this.playerRepository = playerRepository;
	}
	
	public List<Player> getAllPlayers(){
		return playerRepository.findAll();
	}
	
	public void addPlayer(Player player) {
		playerRepository.save(player);
	}
	
	public void deletePlayer(Long id) {
		playerRepository.deleteById(id);
	}
	
	public void updatePlayer(Player player) {
		playerRepository.save(player);
	}
	
	public Player getPlayerById(Long id) {
		return playerRepository.findById(id).get();
	}

	
	public void deletePlaylistById(Long id) {
		playerRepository.deleteById(id);	
	}
	
	public void updatePlayerStatus(Long id) {
		Player player = playerRepository.findById(id).get();
		player.setStatus("sold");
		playerRepository.save(player);
	}
}
