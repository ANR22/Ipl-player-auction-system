package com.websocket.team;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class TeamService {
	
private TeamRepository teamRepository;
	

	public TeamService(TeamRepository teamRepository) {
		super();
		this.teamRepository = teamRepository;
	}
	
	public List<Team> getAllTeams(){
		return teamRepository.findAll();
	}
	
	public void addTeam(Team team) {
		teamRepository.save(team);
	}
	
	public void deleteTeam(Long id) {
		teamRepository.deleteById(id);
	}
	
	public Team getTeamByLoginId(String loginId) {
		return teamRepository.findByLoginId(loginId);
	}
	
	public Team getTeamById(Long teamId) {
		if(teamId==null)
			return null;
		return teamRepository.findById(teamId).get();
	}
	
}
