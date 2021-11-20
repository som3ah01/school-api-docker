package com.metadata.school.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
import com.metadata.school.mapper.CourseCustomIgnoreMapper;
import com.metadata.school.mapper.CourseMapper;
import com.metadata.school.mapper.StudentMapper;
import com.metadata.school.mapper.StudntCustomIgnoreMapper;
import com.metadata.school.service.CourseService;
import com.metadata.school.service.StudentService;

@ExtendWith(SpringExtension.class)
 class ReportControllerTest {

	private static final Integer STUDENT_ID = 1;
	private static final Integer COURSE_ID = 1;

	private @InjectMocks ReportController reportController;
	private @Mock StudentService studentService;
	private @Mock StudentMapper studentMapper;
	private @Mock CourseService courseService;
	private @Mock CourseMapper courseMapper;
	private @Mock CourseCustomIgnoreMapper courseCustomIgnoreMapper;
	private @Mock StudntCustomIgnoreMapper studntCustomIgnoreMapper;


	@Test
	void Should_ReturnStudentDTOList_When_ProvidesCourseId() {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		Student student = new Student(1, "student Name", new HashSet<>());
		course.getStudents().add(student);
		
		StudentDTO studentDto = new StudentDTO(1, "student Name", new HashSet<>());
		CourseDTO courseDto = new CourseDTO(1, "course 1", new HashSet<>());
		studentDto.getCourses().add(courseDto);
		List<StudentDTO> studentsDTO = Arrays.asList(studentDto);
		// When
		Mockito.when(courseService.getCourseById(Mockito.anyInt())).thenReturn(course);
		Mockito.when(studentMapper.convertListToDto(Mockito.any())).thenReturn(studentsDTO);
		// Then
		ResponseEntity<List<StudentDTO>> resualt = reportController.getStudentsByCourseId(STUDENT_ID);
		// AND
		assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(resualt.getBody().get(0).getCourses().size()).isEqualTo(1);
	}

	@Test
	void Should_ReturnCourseDTOList_When_ProvidesStudentId() {
		// Given
		Student student = new Student(1, "student Name", new HashSet<>());
		Course course = new Course(1, "course 1", new HashSet<>());
		student.getCourses().add(course);
		
		CourseDTO courseDto = new CourseDTO(1, "course 1", new HashSet<>());
		StudentDTO studentDto = new StudentDTO(1, "student Name", new HashSet<>());
		courseDto.getStudents().add(studentDto);
		List<CourseDTO> coursesDTO = Arrays.asList(courseDto);
		// When
		Mockito.when(studentService.getStudentById(Mockito.anyInt())).thenReturn(student);
		Mockito.when(courseMapper.convertListToDto(Mockito.any())).thenReturn(coursesDTO);
		// Then
		ResponseEntity<List<CourseDTO>> resualt = reportController.getCoursesByStudentId(COURSE_ID);
		// AND
		assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(resualt.getBody().get(0).getStudents().size()).isEqualTo(1);
	}

	@Test
	void Should_ReturnStudentDTOListWithoutCourses_When_ProvidesCourseId() {
		// Given
				Course course = new Course(1, "course 1", new HashSet<>());
				Student student = new Student(1, "student Name", new HashSet<>());
				course.getStudents().add(student);
				
				StudentDTO studentDto = new StudentDTO(1, "student Name", new HashSet<>());
				List<StudentDTO> studentDTOList = Arrays.asList(studentDto);
				// When
				Mockito.when(courseService.getCourseById(Mockito.anyInt())).thenReturn(course);
				Mockito.when(studntCustomIgnoreMapper.convertToDTOListWithoutCourses(Mockito.any())).thenReturn(studentDTOList);
				// Then
				ResponseEntity<List<StudentDTO>> resualt = reportController.getStudentsByCourseIdWithouCourses(STUDENT_ID);
				// AND
				assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.OK);
				assertThat(resualt.getBody().get(0).getCourses()).isNullOrEmpty();
	}

	@Test
	void Should_ReturnCourseDTOListWithoutStudents_When_ProvidesStudentId() {
		// Given
		Student student = new Student(1, "student Name", new HashSet<>());
		Course course = new Course(1, "course 1", new HashSet<>());
		student.getCourses().add(course);
		
		CourseDTO courseDto = new CourseDTO(1, "course 1", new HashSet<>());
		List<CourseDTO> courseDTOList = Arrays.asList(courseDto);
		
		// When
		Mockito.when(studentService.getStudentById(Mockito.anyInt())).thenReturn(student);
		Mockito.when(courseCustomIgnoreMapper.convertToDTOListWithoutStudents(Mockito.any())).thenReturn(courseDTOList);
		// Then
		ResponseEntity<List<CourseDTO>> resualt = reportController.getCoursesByStudentIdWithoutStudents(COURSE_ID);
		// AND
		assertThat(resualt.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(resualt.getBody().get(0).getStudents()).isNullOrEmpty();
	}

}
