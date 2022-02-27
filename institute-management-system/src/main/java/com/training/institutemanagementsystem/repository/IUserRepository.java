package com.training.institutemanagementsystem.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.training.institutemanagementsystem.constant.UserType;
import com.training.institutemanagementsystem.model.Users;



public interface IUserRepository  extends JpaRepository<Users, Integer> {
	
	
	@Query("Select u FROM Users u where u.type=:type" )
	public  List<Users> findByType(@Param("type") UserType type);
	
	
	@Query("Select u FROM Users u where u.type=:type and u.branch=:branch" )
	public  List<Users> findByTypeAndBranch(@Param("type") UserType type,@Param("branch") String branch);
	
	@Query("Select u FROM Users u where u.userId=:userId " )
	public Users findByUserId(@Param("userId") String userId);
	
	

}
