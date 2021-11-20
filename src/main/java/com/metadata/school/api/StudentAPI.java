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

import com.metadata.school.dto.StudentDTO;


@RequestMapping("/v1/student")
public interface StudentAPI {
	
	@GetMapping("/{studentId}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer studentId);
	
	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAllStudents();
	
	@PostMapping("/")
	public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO);
	
	@PutMapping("/{studentId}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer studentId,@RequestBody StudentDTO studentDTO);
	
	@DeleteMapping("/{studentId}")
	public ResponseEntity<HttpStatus> deleteStudentById(@PathVariable Integer studentId);
	

}
