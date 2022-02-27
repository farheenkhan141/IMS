package com.training.institutemanagementsystem.constant;

public enum UserType {
	
	FACULTY, STUDENT, ADMIN;
	
	public static UserType toEnum(String userType) {
		if(userType.equalsIgnoreCase("admin")) {
			return UserType.ADMIN;
		}else if(userType.equalsIgnoreCase("faculty")) {
			return UserType.FACULTY;
		}else if(userType.equalsIgnoreCase("student")) {
			return UserType.STUDENT;
		}
		return null;
		
	}

}
