package com.training.institutemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.institutemanagementsystem.dto.SubjectDto;
import com.training.institutemanagementsystem.dto.TimeTableDetails;
import com.training.institutemanagementsystem.dto.TimeTableDto;
import com.training.institutemanagementsystem.model.Subject;
import com.training.institutemanagementsystem.model.TimeTable;
import com.training.institutemanagementsystem.service.IAdminService;
import com.training.institutemanagementsystem.service.ImsService;

@RestController
@RequestMapping("/ims/admin")
public class AdminController {
	
	@Autowired
	private ImsService imsService;
	
	@Autowired 
	private IAdminService adminService;
	
	
	
	@PostMapping("/add/timetable")
	public void addTimeTable(@RequestBody List<TimeTableDto> timeTables) {
		for(TimeTableDto timeTable:timeTables) {
			TimeTable timeTableSaved=adminService.addTimeTable(timeTable);	
		}
		
	}
	
	@PutMapping("/edittimetable")
	public void editTimeTable(@RequestBody TimeTableDto timetable) {
		
	}
	
	@DeleteMapping("/delete/timetable")
	public void deleteTimeTable(@RequestParam int id) {
		
	}
	
	@GetMapping("/get/timetable/byday")
	public List<TimeTable> getTimeTableByDay(@RequestParam int year,int sem,String day,String branch) {
		List<TimeTable> timeTables=adminService.getTimeTableByDay(day, year, sem,branch);
		return timeTables;
	}
	
	@GetMapping("/get/timetable/byweek")
	public List<TimeTableDetails> getTimeTableByWeek(@RequestParam int year,int sem, String branch) {
		
		List<TimeTableDetails> timeTables=adminService.getTimeTableByWeek(year,sem,branch);
		return timeTables;
	}
	
	@PostMapping("/add/subject")
	public SubjectDto addSubject(@RequestBody SubjectDto subject) {
		SubjectDto subjectSaved=adminService.addSubject(subject);
		return subjectSaved;
	}
	
	@PutMapping("/edit/subject")
	public void editTSubject(@RequestBody Subject subject) {
		
		Subject subjectUpdate=adminService.editSubject(subject);
		
	}
	
	@DeleteMapping("/delete/subject")
	public void deleteSubject(@RequestParam int id) {
		
		
	}
	
	@GetMapping("/get/subject/byId")
	public Subject getAllSubjectById(@RequestParam int id) {

		Subject subject=adminService.getSubjectById(id);
		return subject;
	}
	@GetMapping("/getall/subject")
	public List<Subject> getAllSubject() {
		List<Subject> subjects=adminService.getAllSubject();
		return subjects;
	}
	
	@GetMapping("/get/subject/bybranch")
	public List<Subject> getSubjectByBranch(@RequestParam String branch){
		List<Subject> subjects=adminService.getSubjectByBranch(branch);
		return subjects;
	}
	
	
	
	
	
	
	
	
	
	

}
