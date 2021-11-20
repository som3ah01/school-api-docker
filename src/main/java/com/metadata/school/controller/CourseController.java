package com.metadata.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.metadata.school.api.CourseAPI;
import com.metadata.school.dto.CourseDTO;
import com.metadata.school.mapper.CourseMapper;
import com.metadata.school.service.CourseService;

@RestController
public class CourseController implements CourseAPI {
	
	
	
	private @Autowired CourseService courseService;
	private @Autowired CourseMapper courseMapper;
	


	@Override
	public ResponseEntity<CourseDTO> getCourseById(Integer courseId) {
		return new ResponseEntity<>(courseMapper.convertToDto(courseService.getCourseById(courseId)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CourseDTO>> getAllCourses() {
		return new ResponseEntity<>(courseMapper.convertListToDto(courseService.getAllCourses()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CourseDTO> createCourse(CourseDTO courseDTO) {
		return new ResponseEntity<>(courseMapper.convertToDto(courseService.createCourse(courseMapper.convertFromDto(courseDTO))), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CourseDTO> updateCourse(Integer courseId, CourseDTO courseDTO) {
		return new ResponseEntity<>(courseMapper.convertToDto(courseService.updateCourse(courseId, courseMapper.convertFromDto(courseDTO))), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<HttpStatus> deleteCourseById(Integer courseId) {
		courseService.deleteCourseById(courseId);
		return new ResponseEntity<>( HttpStatus.NO_CONTENT);
	}
	
	

}
