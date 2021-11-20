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
import com.metadata.school.repo.CourseRepo;
import com.metadata.school.service.CourseService;
import com.metadata.school.service.impl.CourseServiceImpl;

@ExtendWith(SpringExtension.class)
 class CourseControllerTest {

	private static final Integer COURSE_ID = 1;

	private @InjectMocks CourseController courseController;
	private @Mock CourseService courseService;
	private @Mock CourseMapper courseMapper;

	@Test
	void Should_ReturnCourseDTO_When_ProvidesCourseId() {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		Student student = new Student(1, "student Name", new HashSet<>());
		course.getStudents().add(student);
		
		CourseDTO courseDto = new CourseDTO(1, "course 1", new HashSet<>());
		StudentDTO studentDto = new StudentDTO(1, "student Name", new HashSet<>());
		courseDto.getStudents().add(studentDto);
		// When
		Mockito.when(courseService.getCourseById(Mockito.anyInt())).thenReturn(course);
		Mockito.when(courseMapper.convertToDto(Mockito.any())).thenReturn(courseDto);
		// Then
		ResponseEntity<CourseDTO> resualt = courseController.getCourseById(COURSE_ID);
		// AND
		assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(resualt.getBody().getName()).isEqualTo(course.getName());
	}

	@Test
	void Should_CreateCourseDTO_When_ProvideCourse() {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		Student student = new Student(1, "student Name", new HashSet<>());
		course.getStudents().add(student);
		
		CourseDTO courseDto = new CourseDTO(1, "course 1", new HashSet<>());
		StudentDTO studentDto = new StudentDTO(1, "student Name", new HashSet<>());
		courseDto.getStudents().add(studentDto);
		// When
		Mockito.when(courseService.createCourse(Mockito.any())).thenReturn(course);
		Mockito.when(courseMapper.convertToDto(Mockito.any())).thenReturn(courseDto);
		// Then
		ResponseEntity<CourseDTO> resualt = courseController.createCourse(new CourseDTO());
		// AND
		assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(resualt.getBody().getName()).isEqualTo(course.getName());
	}

	@Test
	void Should_UpdateCourse_When_ProvideCourse() {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		Student student = new Student(1, "student Name", new HashSet<>());
		course.getStudents().add(student);
		
		CourseDTO courseDto = new CourseDTO(1, "course 1", new HashSet<>());
		StudentDTO studentDto = new StudentDTO(1, "student Name", new HashSet<>());
		courseDto.getStudents().add(studentDto);
		// When
		Mockito.when(courseService.updateCourse(Mockito.anyInt(), Mockito.any())).thenReturn(course);
		Mockito.when(courseMapper.convertToDto(Mockito.any())).thenReturn(courseDto);
		// Then
		ResponseEntity<CourseDTO> resualt = courseController.updateCourse(COURSE_ID,new CourseDTO());
		// AND
		assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(resualt.getBody().getName()).isEqualTo(course.getName());
	}

	@Test
	void Should_DeleteCourse_When_ProvideCourse() {
		
		// When
		Mockito.doNothing().when(courseService).deleteCourseById(COURSE_ID);
		// Then
		ResponseEntity<HttpStatus> resualt = courseController.deleteCourseById(COURSE_ID);
		// AND
		assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
