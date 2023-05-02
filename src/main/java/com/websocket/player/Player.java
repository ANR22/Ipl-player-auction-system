package com.websocket.player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;

	@Column(name="basePrice", nullable=false)
	private double basePrice;
	
	@Column(name="status", nullable=false)
	private String status;
	
	@Column(name="imgUrl", nullable=false)
	private String imgUrl;
	
	public Player() {
		super();
	}






	public Player(Long id, String name, double basePrice, String status, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.basePrice = basePrice;
		this.status = status;
		this.imgUrl = imgUrl;
	}






	public String getImgUrl() {
		return imgUrl;
	}






	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}






	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public double getBasePrice() {
		return basePrice;
	}


	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
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
	
	public String getString() {
		return "{\"id\":\""+id+"\",\"name\":\""+name+"\",\"basePrice\":\""+basePrice+"\",\"imgUrl\":\""+imgUrl+"\"";
	}
	
}
