package com.websocket.team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long>{
	public Team findByLoginId(String loginId);
}
