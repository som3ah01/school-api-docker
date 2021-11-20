package com.metadata.school.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.metadata.school.entity.Course;
import com.metadata.school.exceptions.ResourceNotFoundException;
import com.metadata.school.repo.CourseRepo;
import com.metadata.school.service.CourseService;
import com.metadata.school.util.GlobalConstants;
import com.metadata.school.util.GlobalEnums;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepo courseRepo;
	
	
	@Override
	public Course getCourseById(Integer courseId) {
		Assert.notNull(courseId, String.format(GlobalConstants.MISSING_MANDATORY_VALUE_MSG, "courseId"));
		 return courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(String.format(GlobalConstants.COURSE_NOT_FOUND, courseId) , GlobalEnums.Errors.RESOURCE_NOT_FUND.toString()));
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepo.findAll();
	}
	
	@Override
	public Course updateCourse(Integer courseId, Course course) {
		Assert.notNull(courseId, String.format(GlobalConstants.MISSING_MANDATORY_VALUE_MSG, "courseId"));
		courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(String.format(GlobalConstants.COURSE_NOT_FOUND, courseId),
				GlobalEnums.Errors.RESOURCE_NOT_FUND.toString()));
		return courseRepo.save(course);
	}

	@Override
	public Course createCourse(Course course) {
		 return courseRepo.save(course);
	}

	@Override
	public void deleteCourseById(Integer courseId) {
		Assert.notNull(courseId, String.format(GlobalConstants.MISSING_MANDATORY_VALUE_MSG, "courseId"));
		courseRepo.deleteById(courseId);
	}

}
