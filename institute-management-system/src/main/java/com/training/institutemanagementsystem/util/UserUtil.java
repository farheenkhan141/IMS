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
		user.setPassword(request.getPassword());
		user.setPhoneNo(request.getPhoneNo());
		
//		String userId=request.getName().substring(0, 3).concat(request.getBranch().substring(0, 2)+""+request.getPhoneNo().subSequence(0, 4));
//		System.out.println(userId);
//		user.setUserId(userId);
		user.setUserId(request.getUserId());
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

