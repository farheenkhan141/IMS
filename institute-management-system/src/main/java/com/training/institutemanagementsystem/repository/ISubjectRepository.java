package com.training.institutemanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.training.institutemanagementsystem.model.Subject;


public interface ISubjectRepository extends JpaRepository<Subject,Integer> {

	
	@Query("Select s FROM Subject s where s.year=:year and s.sem=:sem" )
	 List<Subject> findByYearaAndSem(@Param("year") int year, @Param("sem") int sem);
	
	@Query("Select s FROM Subject s where s.subjectCode=:code" )
	public Subject findBySubjectCode(@Param("code") String code);
	
	@Query("Select s FROM Subject s where s.branch=:branch" )
	List<Subject> findByBranch(@Param("branch") String branch);
	
	
}
