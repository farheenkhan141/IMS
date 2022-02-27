package com.training.institutemanagementsystem.service;

import java.util.List;


import com.training.institutemanagementsystem.dto.CreateUsersDetails;
import com.training.institutemanagementsystem.dto.UpdateUserDto;
import com.training.institutemanagementsystem.dto.UserDetails;
import com.training.institutemanagementsystem.model.Users;



public interface ImsService {
	
	public UserDetails addUser(CreateUsersDetails user);
	
	public List<UserDetails> getAllUsersByType(String type);
	
	public List<UserDetails> getAllUsersByBranch(String type,String branch);
	public List<UserDetails> getAllUsers();
	

	
	public void deleteUser(int id);
	
	public Users authenticateUser(String userId,String password);
	
	public UserDetails findUserById(int id);

	public UserDetails updateUser(UpdateUserDto update);
	public UserDetails editProfile(UpdateUserDto update);

}
