package com.metadata.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.metadata.school.api.RegistertionCourseAPI;
import com.metadata.school.dto.StudentDTO;
import com.metadata.school.entity.Student;
import com.metadata.school.mapper.StudentMapper;
import com.metadata.school.service.RegistertionCourseService;

@RestController
public class RegistertionCourseController implements RegistertionCourseAPI {
	
	private @Autowired RegistertionCourseService registertionCourseService;
	private @Autowired StudentMapper studentMapper;

	@Override
	public ResponseEntity<StudentDTO> registerStudenttoCourse(Integer studentId, Integer courseId) {
		 Student student = registertionCourseService.registerStudenttoCourse(studentId,courseId);
		 return new ResponseEntity<>(studentMapper.convertToDto(student),  HttpStatus.OK);
	}

}
