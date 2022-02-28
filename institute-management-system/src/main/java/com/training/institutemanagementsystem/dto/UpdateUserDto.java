package com.training.institutemanagementsystem.dto;

import lombok.Data;

@Data
public class UpdateUserDto {

	private int id;
	private String name;
	private String branch;
	private String type;
	private String email;
	private String phoneNo;
	
}
