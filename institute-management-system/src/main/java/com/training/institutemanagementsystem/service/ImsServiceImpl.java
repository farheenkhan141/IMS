package com.training.institutemanagementsystem.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.institutemanagementsystem.constant.UserType;
import com.training.institutemanagementsystem.dto.CreateUsersDetails;
import com.training.institutemanagementsystem.dto.UpdateUserDto;
import com.training.institutemanagementsystem.dto.UserDetails;
import com.training.institutemanagementsystem.exception.UserExistException;
import com.training.institutemanagementsystem.model.Subject;
import com.training.institutemanagementsystem.model.Users;
import com.training.institutemanagementsystem.repository.ISubjectRepository;
import com.training.institutemanagementsystem.repository.IUserRepository;
import com.training.institutemanagementsystem.util.UserUtil;


@Transactional
@Service
public class ImsServiceImpl implements ImsService {

	
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private ISubjectRepository subjectRepo;
	
	@Autowired
	private UserUtil userUtil;

	@Override
	public UserDetails addUser(CreateUsersDetails user)  {
		
		try {
			Users savedUser=userRepo.save(userUtil.requestToObject(user));
			return userUtil.toDetails(savedUser);
		}catch (Exception e) {
			
			throw new UserExistException("user Already exist");
		}
		
	}
	

	@Override
	public List<UserDetails> getAllUsersByType(String type) {
		List<Users> users=userRepo.findByType(UserType.toEnum(type));
	
		
		return userUtil.toDetailsList(users);
	}
	
	@Override
	public List<UserDetails> getAllUsers(){
		List<Users> users=userRepo.findAll();
		return userUtil.toDetailsList(users);
		
	}

	@Override
	public UserDetails updateUser(UpdateUserDto update) {
		Users user=userRepo.findById(update.getId()).get();
		user.setName(update.getName());
		user.setPhoneNo(update.getPhoneNo());
		user.setEmail(update.getEmail());
		user.setType(UserType.toEnum(update.getType()));
		user.setBranch(update.getBranch());
		
	return userUtil.toDetails(user);
	}
	
	@Override
	public boolean changePassword(int id, String oPassword,String nPassword) {
		
		Users user=userRepo.findById(id).get();
		if(user.getPassword().equals(oPassword)) {
			user.setPassword(nPassword);
			
			return true;
		}
		else {
			return false;
		}
	}


	@Override
	public void deleteUser(int id) {
		userRepo.deleteById(id);		
	}

	@Override
	public Users authenticateUser(String userId,String password) {
		Users 	user= userRepo.findByUserId(userId);;
		
		
		if(user==null) {
			return null;
		}else if((userId.equals(user.getUserId()))&&(password.equals(user.getPassword()))) {
			return user;
		}
		return user;
			
	}

	@Override
	public UserDetails findUserById(int id) {
		Optional<Users> user=userRepo.findById(id);
		if(!user.isPresent()) {
			System.out.println("no such user is in db");
		}
		return userUtil.toDetails(user.get());
	}

	@Override
	public List<UserDetails> getAllUsersByBranch(String type,String branch) {
		List<Users> users=userRepo.findByTypeAndBranch((UserType.toEnum(type)),branch);
		return userUtil.toDetailsList(users);
		
	}

	
	

}
