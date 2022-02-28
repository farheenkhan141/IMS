package com.training.institutemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.institutemanagementsystem.dto.CreateUsersDetails;
import com.training.institutemanagementsystem.dto.UpdateUserDto;
import com.training.institutemanagementsystem.dto.UserDetails;
import com.training.institutemanagementsystem.model.Users;
import com.training.institutemanagementsystem.service.ImsService;

@RestController
@RequestMapping("/ims")
public class IMSController {
	
	@Autowired
	private ImsService service;
	
	@PostMapping("/add")
	public UserDetails addAdmin(@RequestBody CreateUsersDetails user) {
		UserDetails response=service.addUser(user);
		return response;
	}
	 
	@GetMapping("/getallusers/bytype")
	public List<UserDetails> getAllUsersByType(@RequestParam String type) {
		
		List<UserDetails> faculties=service.getAllUsersByType(type);
		return faculties;
		
	}
	@GetMapping("/getuser/byid")
	public UserDetails getUserById(@RequestParam int id) {
		UserDetails user=service.findUserById(id);
		return user;
		
	}
	@GetMapping("/getallusers")
	public List<UserDetails> getAllUsers() {
		List<UserDetails> faculties=service.getAllUsers();
		return faculties;	
	}
	@GetMapping("/getallusersby/branch")
	public List<UserDetails> getAllUsersByBranch(@RequestParam String type, @RequestParam String branch) {
		List<UserDetails> faculties=service.getAllUsersByBranch(type,branch);
		return faculties;	
	}
	
	@PutMapping("/edit/user")
	public UserDetails updateUser(@RequestBody UpdateUserDto user) {
		UserDetails userUpdate=service.updateUser(user);
		return userUpdate;
		
	}
	@GetMapping("/changepassword")
	public boolean changePassword(@RequestParam int id,@RequestParam String oldPassword,@RequestParam String newPassword) {
	
		boolean message=service.changePassword(id,oldPassword,newPassword);
		return message;
		
	}
	
	@DeleteMapping("/delete/user")
	public void deleteUser(@RequestParam int id) {
		service.deleteUser(id);
	}
	
	@PostMapping("/authenticate/user")
	public Users authenticateUser(@RequestParam String userId,@RequestParam String password ) {
		
		Users user=service.authenticateUser(userId, password);
		return user;
	}

}
