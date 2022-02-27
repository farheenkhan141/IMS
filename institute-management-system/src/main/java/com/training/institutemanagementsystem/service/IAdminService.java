package com.training.institutemanagementsystem.service;

import java.util.List;

import com.training.institutemanagementsystem.dto.SubjectDto;
import com.training.institutemanagementsystem.dto.TimeTableDetails;
import com.training.institutemanagementsystem.dto.TimeTableDto;
import com.training.institutemanagementsystem.model.Subject;
import com.training.institutemanagementsystem.model.TimeTable;

public interface IAdminService {

	
	TimeTable addTimeTable(TimeTableDto request);
	List<TimeTable> getTimeTableByDay(String day,int year,int sem,String brnach);
	List<TimeTableDetails> getTimeTableByWeek(int year,int sem,String branch);
	
	
	SubjectDto addSubject(SubjectDto request);
	Subject editSubject(Subject update);
	List<Subject> getAllSubject();
	List<Subject> getSubjectByBranch(String branch);
	Subject getSubjectById(int id);
	void deleteSubject(int id);
	
	

	
}
