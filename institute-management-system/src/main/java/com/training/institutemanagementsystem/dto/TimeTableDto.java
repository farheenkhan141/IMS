package com.training.institutemanagementsystem.dto;




import lombok.Data;


@Data
public class TimeTableDto {
	
	private String day;
	private int year;
	private int sem;
	private String time;
	private String subjectCode;
	private String userId;
	private String branch;

}
