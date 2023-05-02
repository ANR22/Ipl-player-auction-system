package com.websocket.auction;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void queryDatabase(SoldPlayer soldPlayer) {
        // Execute a SQL query using JdbcTemplate
    	String sql = "";
    	
    	if(soldPlayer.getTeamId() == null) {
    		sql = "INSERT INTO unsold_player(player_id,basePrice) VALUES(?,?);";
        	jdbcTemplate.update(sql,soldPlayer.getPlayerId(),soldPlayer.getBidAmount());
    	}
    		
    	else
    	{

    		sql = "INSERT INTO sold_player(player_id,team_id,bid_amount) VALUES(?,?,?);";
        	jdbcTemplate.update(sql,soldPlayer.getPlayerId(),soldPlayer.getTeamId(),soldPlayer.getBidAmount());
    	}
    	
    }

}
