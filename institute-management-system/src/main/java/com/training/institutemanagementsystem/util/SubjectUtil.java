package com.training.institutemanagementsystem.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.training.institutemanagementsystem.dto.SubjectDto;
import com.training.institutemanagementsystem.model.Subject;

@Component
public class SubjectUtil {
	
	public Subject toObject(SubjectDto request) {
		Subject subject=new Subject();
		subject.setName(request.getName());

		subject.setSem(request.getSem());
		subject.setYear(request.getYear());
		subject.setSubjectCode(request.getSubjectCode());
		subject.setBranch(request.getBranch());
		return subject;
	}

	public SubjectDto toDetails(Subject request) {
		SubjectDto subject=new SubjectDto();
		subject.setName(request.getName());

		subject.setSem(request.getSem());
		subject.setYear(request.getYear());
		subject.setSubjectCode(request.getSubjectCode());
		subject.setBranch(request.getBranch());
		return subject;
	}
	
	public List<SubjectDto> toDetailsList(List<Subject> subjects) {
		List<SubjectDto> subjectDetails=new ArrayList<>();
		for(Subject subject:subjects) {
			subjectDetails.add(toDetails(subject));
		}
		return subjectDetails;
	}
	
	
	
}
