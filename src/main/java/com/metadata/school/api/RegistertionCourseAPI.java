package com.metadata.school.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metadata.school.dto.StudentDTO;

@RequestMapping("/v1/register")
public interface RegistertionCourseAPI {
	
	@GetMapping("/student/{studentId}/to/course/{courseId}")
	public ResponseEntity<StudentDTO> registerStudenttoCourse(@PathVariable Integer studentId, @PathVariable Integer courseId);

}
