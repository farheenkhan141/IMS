package com.training.institutemanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.training.institutemanagementsystem.model.TimeTable;


public interface ITimeTableRepository  extends JpaRepository<TimeTable,Integer>{

	@Query("Select t FROM TimeTable t where day=:day and t.year=:year and t.sem=:sem and t.branch=:branch" )
	public  List<TimeTable> findByDay(@Param("day") String day,@Param("year") int year, @Param("sem") int sem,@Param("branch") String branch);
	
	@Query("Select t FROM TimeTable t where t.year=:year and t.sem=:sem and t.branch=:branch" )
	public List<TimeTable> findByWeek(@Param("year") int year, @Param("sem") int sem,@Param("branch") String branch);
	
}
