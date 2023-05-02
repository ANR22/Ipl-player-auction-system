package com.websocket.admin;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.websocket.auction.DatabaseService;
import com.websocket.player.Player;



@Controller
public class AdminController {

//	@Autowired
//	private AdminService adminService;

	@Autowired
	private DatabaseService ds;
	
	@PostMapping("/admin/login")
	public String getLoginForm(@ModelAttribute("admin") Admin admin) {
		if(admin.getUsername().equals("user") && admin.getPassword().equals("password")) {
			return "dashboard";
		}
        return "home";
	}
	@GetMapping("/admin/login")
	public String getLoginPage() {
		return "home";
	}
	
	@GetMapping("/dashboard")
	public String dashboardPage() {
		return "dashboard";
	}
	
	@GetMapping("/admin/logout")
	public String homePage() {
		return "home";
	}
	
	
}
