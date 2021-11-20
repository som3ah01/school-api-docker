package com.metadata.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.metadata.school.api.StudentAPI;
import com.metadata.school.dto.StudentDTO;
import com.metadata.school.entity.Student;
import com.metadata.school.mapper.StudentMapper;
import com.metadata.school.service.StudentService;

@RestController
public class StudentController implements StudentAPI {
	
	
	private @Autowired StudentService studentService;
	private @Autowired StudentMapper studentMapper;
	
	@GetMapping("/hi")
	public ResponseEntity<String> testHello (){
		return new ResponseEntity<>("hhh", HttpStatus.OK);
	}
	@Override
	public ResponseEntity<StudentDTO> getStudentById(Integer studentId) {
		return new ResponseEntity<>(studentMapper.convertToDto(studentService.getStudentById(studentId)), HttpStatus.OK);
	
	}
	@GetMapping("/v2/{studentId}")
	public ResponseEntity<Student> getStudentById2(@PathVariable Integer studentId) {
//		return new ResponseEntity<>(studentMapper.convertToDto(studentService.getStudentById(studentId)), HttpStatus.OK);
		return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
	
	}
	
	@Override
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		return new ResponseEntity<>(studentMapper.convertListToDto(studentService.getAllStudent()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<StudentDTO> createStudent(StudentDTO studentDTO) {
		return new ResponseEntity<>(studentMapper.convertToDto(studentService.createStudent(studentMapper.convertFromDto(studentDTO))), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<StudentDTO> updateStudent(Integer studentId, StudentDTO studentDTO) {
		return new ResponseEntity<>(studentMapper.convertToDto(studentService.updateStudent(studentId,studentMapper.convertFromDto(studentDTO))), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<HttpStatus> deleteStudentById(Integer studentId) {
		studentService.deleteStudentById(studentId);
		return new ResponseEntity<>( HttpStatus.NO_CONTENT);
	}
	
	

}
