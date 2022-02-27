package com.training.institutemanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class TimeTable {
	
	@GeneratedValue
	@Id
	private int id;
	
	private String day;
	private int year;
	private int sem;
	private String branch;
	private String time;
	@OneToOne
	private Subject sub;
	
	@OneToOne
	private Users user;

}
