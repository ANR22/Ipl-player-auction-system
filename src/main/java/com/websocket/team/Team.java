package com.websocket.team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="team_name", nullable=false)
	private String name;
	
	@Column(name="loginId", nullable=false)
	private String loginId;
	
	@Column(name="password", nullable=false)
	private String password;

	@Column(name="PurseRemaining", nullable=false)
	private double purseRemaining;
	
	@Column(name="NumPlayersBought", nullable=false)
	private int numPlayersBought;
	
	public Team() {
		super();
	}
	
	
	public Team(Long id, String name, String loginId, String password, double purseRemaining, int numPlayersBought) {
		super();
		this.id = id;
		this.name = name;
		this.loginId = loginId;
		this.password = password;
		this.purseRemaining = purseRemaining;
		this.numPlayersBought = numPlayersBought;
	}


	public double getPurseRemaining() {
		return purseRemaining;
	}


	public void setPurseRemaining(double purseRemaining) {
		this.purseRemaining = purseRemaining;
	}


	public int getNumPlayersBought() {
		return numPlayersBought;
	}


	public void setNumPlayersBought(int numPlayersBought) {
		this.numPlayersBought = numPlayersBought;
	}


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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	
	
}
