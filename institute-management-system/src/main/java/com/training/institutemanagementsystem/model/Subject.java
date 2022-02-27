package com.training.institutemanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Subject {
	
	@GeneratedValue
	@Id
	private int id;
	private String name;
	private String subjectCode;
	private int sem;
	private int year;
	private String branch;

}
