package com.metadata.school.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.metadata.school.dto.CourseDTO;
import com.metadata.school.dto.StudentDTO;
import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;
import com.metadata.school.mapper.CourseMapper;
import com.metadata.school.mapper.StudentMapper;
import com.metadata.school.repo.CourseRepo;
import com.metadata.school.service.CourseService;
import com.metadata.school.service.StudentService;
import com.metadata.school.service.impl.CourseServiceImpl;

@ExtendWith(SpringExtension.class)
 class StudentControllerTest {

	private static final Integer STUDENT_ID = 1;

	private @InjectMocks StudentController studentController;
	private @Mock StudentService studentService;
	private @Mock StudentMapper studentMapper;

	@Test
	void Should_ReturnStudentDTO_When_ProvidesCourseId() {
		// Given
		Student student = new Student(1, "student Name", new HashSet<>());
		Course course = new Course(1, "course 1", new HashSet<>());
		student.getCourses().add(course);
		
		StudentDTO studentDto = new StudentDTO(1, "student Name", new HashSet<>());
		CourseDTO courseDto = new CourseDTO(1, "course 1", new HashSet<>());
		studentDto.getCourses().add(courseDto);
		// When
		Mockito.when(studentService.getStudentById(Mockito.anyInt())).thenReturn(student);
		Mockito.when(studentMapper.convertToDto(Mockito.any())).thenReturn(studentDto);
		// Then
		ResponseEntity<StudentDTO> resualt = studentController.getStudentById(STUDENT_ID);
		// AND
		assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(resualt.getBody().getName()).isEqualTo(student.getName());
	}

	@Test
	void Should_CreateStudentDTO_When_ProvideCourse() {
		// Given
		Student student = new Student(1, "student Name", new HashSet<>());
		Course course = new Course(1, "course 1", new HashSet<>());
		student.getCourses().add(course);
		
		StudentDTO studentDto = new StudentDTO(1, "student Name", new HashSet<>());
		CourseDTO courseDto = new CourseDTO(1, "course 1", new HashSet<>());
		studentDto.getCourses().add(courseDto);
		// When
		Mockito.when(studentService.createStudent(Mockito.any())).thenReturn(student);
		Mockito.when(studentMapper.convertToDto(Mockito.any())).thenReturn(studentDto);
		// Then
		ResponseEntity<StudentDTO> resualt = studentController.createStudent(new StudentDTO());
		// AND
		assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(resualt.getBody().getName()).isEqualTo(student.getName());
	}

	@Test
	void Should_UpdateStudentDTO_When_ProvideCourse() {
		// Given
		Student student = new Student(1, "student Name", new HashSet<>());
		Course course = new Course(1, "course 1", new HashSet<>());
		student.getCourses().add(course);
		
		StudentDTO studentDto = new StudentDTO(1, "student Name", new HashSet<>());
		CourseDTO courseDto = new CourseDTO(1, "course 1", new HashSet<>());
		studentDto.getCourses().add(courseDto);
		// When
		Mockito.when(studentService.updateStudent(Mockito.anyInt(), Mockito.any())).thenReturn(student);
		Mockito.when(studentMapper.convertToDto(Mockito.any())).thenReturn(studentDto);
		// Then
		ResponseEntity<StudentDTO> resualt = studentController.updateStudent(STUDENT_ID,new StudentDTO());
		// AND
		assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(resualt.getBody().getName()).isEqualTo(student.getName());
	}

	@Test
	void Should_DeleteStudentDTO_When_ProvideCourse() {
		
		// When
		Mockito.doNothing().when(studentService).deleteStudentById(STUDENT_ID);
		// Then
		ResponseEntity<HttpStatus> resualt = studentController.deleteStudentById(STUDENT_ID);
		// AND
		assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
