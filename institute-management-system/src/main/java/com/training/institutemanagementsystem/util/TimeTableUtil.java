package com.training.institutemanagementsystem.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.training.institutemanagementsystem.dto.TimeTableDetails;
import com.training.institutemanagementsystem.dto.TimeTableDto;
import com.training.institutemanagementsystem.model.Subject;
import com.training.institutemanagementsystem.model.TimeTable;
import com.training.institutemanagementsystem.model.Users;
import com.training.institutemanagementsystem.repository.ISubjectRepository;
import com.training.institutemanagementsystem.repository.IUserRepository;
import com.training.institutemanagementsystem.service.IAdminService;

@Component
public class TimeTableUtil {

	@Autowired
	private ISubjectRepository subjectRepo;

	@Autowired
	private IUserRepository userRepo;

	public TimeTable toObject(TimeTableDto request) {
		TimeTable timeTable = new TimeTable();
		timeTable.setSem(request.getSem());
		timeTable.setDay(request.getDay());
		timeTable.setYear(request.getYear());
		timeTable.setTime(request.getTime());
		timeTable.setBranch(request.getBranch());
		timeTable.setSub(subjectRepo.findBySubjectCode(request.getSubjectCode()));
		timeTable.setUser(userRepo.findByUserId(request.getUserId()));
		return timeTable;
	}
	
	public TimeTableDetails toDetails(TimeTable timeTable) {
		TimeTableDetails tt=new TimeTableDetails();
		tt.setBranch(timeTable.getBranch());
		tt.setDay(timeTable.getDay());
		tt.setSem(timeTable.getSem());
		tt.setYear(timeTable.getYear());
		tt.setTime(timeTable.getTime());
		
		tt.setSubjectCode(timeTable.getSub().getSubjectCode());
		tt.setUserName(timeTable.getUser().getName());
		return tt;
	}
	
	public List<TimeTableDetails> toDetailsList(List<TimeTable> timeTables){
		List<TimeTableDetails> detailsList=new ArrayList<>();
		for(TimeTable timeTable:timeTables) {
			detailsList.add(toDetails(timeTable));
		}
		return detailsList;
	}

}
