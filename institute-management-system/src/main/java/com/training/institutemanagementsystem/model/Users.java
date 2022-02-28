package com.training.institutemanagementsystem.model;



import com.training.institutemanagementsystem.constant.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;




@Data
@Entity
public class Users {
	
	@GeneratedValue
	@Id
	private int id;
	
	private String name;
	private String branch;
	private UserType type;
	private String userId;
	
	@Column(unique=true)
	private String email;
	private String phoneNo;
	private String password;
	

}
