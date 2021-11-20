package com.metadata.school.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.metadata.school.dto.CourseDTO;
import com.metadata.school.dto.StudentDTO;
import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;
import com.metadata.school.mapper.StudentMapper;
import com.metadata.school.service.RegistertionCourseService;

@ExtendWith(SpringExtension.class)
 class RegistertionCourseControllerTest {
	private static final Integer STUDENT_ID = 1;
	private static final Integer COURSE_ID = 1;
	
	private @InjectMocks RegistertionCourseController registertionCourseController;
	private @Mock RegistertionCourseService registertionCourseService;
	private @Mock StudentMapper studentMapper;

	@Test
	void Should_ReturnStudentDTOWithCourses_When_RegisterStudentToCourse() {
		// Given
		Student student = new Student(1, "student Name", new HashSet<>());
		Course course = new Course(1, "course 1", new HashSet<>());
		student.getCourses().add(course);
		
		StudentDTO studentDto = new StudentDTO(1, "student Name", new HashSet<>());
		CourseDTO courseDto = new CourseDTO(1, "course 1", new HashSet<>());
		studentDto.getCourses().add(courseDto);
		// When
		Mockito.when(registertionCourseService.registerStudenttoCourse(Mockito.anyInt(),Mockito.anyInt())).thenReturn(student);
		Mockito.when(studentMapper.convertToDto(Mockito.any())).thenReturn(studentDto);
		// Then
		ResponseEntity<StudentDTO> resualt = registertionCourseController.registerStudenttoCourse(STUDENT_ID,COURSE_ID);
		// AND
		assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(resualt.getBody().getName()).isEqualTo(student.getName());
	}

}
