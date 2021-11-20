package com.metadata.school.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metadata.school.dto.CourseDTO;

@RequestMapping("/v1/course")
public interface CourseAPI {
	
	@GetMapping("/{courseId}")
	public ResponseEntity<CourseDTO> getCourseById(@PathVariable Integer courseId);
	
	@GetMapping("")
	public ResponseEntity<List<CourseDTO>> getAllCourses();
	
	@PostMapping("/")
	public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO);
	
	@PutMapping("/{courseId}")
	public ResponseEntity<CourseDTO> updateCourse(@PathVariable Integer courseId,@RequestBody CourseDTO courseDTO);
	
	@DeleteMapping("/{courseId}")
	public ResponseEntity<HttpStatus> deleteCourseById(@PathVariable Integer courseId);

}
