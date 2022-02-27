package com.training.institutemanagementsystem.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.training.institutemanagementsystem.constant.UserType;
import com.training.institutemanagementsystem.dto.CreateUsersDetails;
import com.training.institutemanagementsystem.dto.UserDetails;
import com.training.institutemanagementsystem.model.Users;

@Component
public class UserUtil {

	public Users requestToObject(CreateUsersDetails request) {
		Users user = new Users();

		user.setType(UserType.toEnum(request.getType()));
		user.setName(request.getName());
		user.setBranch(request.getBranch());
		user.setEmail(request.getEmail());
		
		user.setPhoneNo(request.getPhoneNo());
		
		String userId=request.getEmail().split("@")[0];
		user.setUserId(userId);
		user.setPassword(userId);

				return user;
	}
	
	public UserDetails toDetails(Users user) {
		UserDetails details=new UserDetails();
		details.setId(user.getId());
		details.setBranch(user.getBranch());
		details.setEmail(user.getEmail());
		details.setName(user.getName());
		details.setUserId(user.getUserId());
		details.setType(user.getType().toString());
		details.setPhoneNo(user.getPhoneNo());
		details.setPassword(user.getPassword());
		return details;
	}
	
	public List<UserDetails> toDetailsList(List<Users> users){
		List<UserDetails> detailsList=new ArrayList<>();
		for(Users user:users) {
			detailsList.add(toDetails(user));
		}
		return detailsList;
	}
	
}

