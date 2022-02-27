package com.training.institutemanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.institutemanagementsystem.dto.SubjectDto;
import com.training.institutemanagementsystem.dto.TimeTableDetails;
import com.training.institutemanagementsystem.dto.TimeTableDto;
import com.training.institutemanagementsystem.model.Subject;
import com.training.institutemanagementsystem.model.TimeTable;
import com.training.institutemanagementsystem.repository.ISubjectRepository;
import com.training.institutemanagementsystem.repository.ITimeTableRepository;
import com.training.institutemanagementsystem.util.SubjectUtil;
import com.training.institutemanagementsystem.util.TimeTableUtil;

@Transactional
@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private TimeTableUtil timeTableUtil;

	@Autowired
	private SubjectUtil subjectUtil;

	@Autowired
	private ITimeTableRepository timeTableRepo;

	@Autowired
	private ISubjectRepository subjectRepo;

	@Override
	public TimeTable addTimeTable(TimeTableDto request) {
		TimeTable timeTableSaved = timeTableRepo.save(timeTableUtil.toObject(request));
		return timeTableSaved;
	}

	@Override
	public List<TimeTable> getTimeTableByDay(String day, int year, int sem, String branch) {
		List<TimeTable> timeTable = timeTableRepo.findByDay(day, year, sem, branch);
		return timeTable;
	}

	@Override
	public List<TimeTableDetails> getTimeTableByWeek( int year, int sem, String branch) {
		List<TimeTable> timeTable = timeTableRepo.findByWeek( year, sem, branch);
		
		return timeTableUtil.toDetailsList(timeTable);
	}

	@Override
	public SubjectDto addSubject(SubjectDto request) {
		Subject subjectSaved = subjectRepo.save(subjectUtil.toObject(request));
		return subjectUtil.toDetails(subjectSaved);
	}
	
	@Override
	public List<Subject> getAllSubject(){
		List<Subject> subjects=subjectRepo.findAll();
		return subjects;
	}

	@Override
	public List<Subject> getSubjectByBranch(String branch) {
		List<Subject> subjects=subjectRepo.findByBranch(branch);
		return subjects;
	}

	@Override
	public void deleteSubject(int id) {
		subjectRepo.deleteById(id);
		
	}

	@Override
	public Subject getSubjectById(int id) {
		Subject subject=subjectRepo.findById(id).get();
		return subject;
	}

	@Override
	public Subject editSubject(Subject update) {
		Subject sub=subjectRepo.findById(update.getId()).get();
		sub.setName(update.getName());
		sub.setBranch(update.getBranch());
		sub.setSem(update.getSem());
		sub.setSubjectCode(update.getSubjectCode());
		sub.setYear(update.getYear());
		Subject subjectSaved = subjectRepo.save(sub);
		return subjectSaved;
	}

	
	

}
