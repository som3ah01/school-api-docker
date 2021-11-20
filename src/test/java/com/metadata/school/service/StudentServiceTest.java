package com.metadata.school.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;
import com.metadata.school.repo.StudentRepo;
import com.metadata.school.service.impl.StudentServiceImpl;

@ExtendWith(SpringExtension.class)
 class StudentServiceTest {

	private static final Integer STUDENT_ID = 1;

	private @InjectMocks StudentServiceImpl studentService;
	private @Mock StudentRepo studentRepo;

	@Test
	void Should_ReturnStudent_When_ProvidesCourseId() {
		// Given
		Student student = new Student(1, "student Name", new HashSet<>());
		Course course = new Course(1, "course 1", new HashSet<>());
		student.getCourses().add(course);
		// When
		Mockito.when(studentRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
		// Then
		Student found = studentService.getStudentById(STUDENT_ID);
		// AND
		assertThat(found.getName()).isEqualTo(student.getName());
	}

	@Test
	void Should_CreateStudent_When_ProvideCourse() {
		// Given
		Student student = new Student(1, "student Name", new HashSet<>());
		Course course = new Course(1, "course 1", new HashSet<>());
		student.getCourses().add(course);
		// When
		Mockito.when(studentRepo.save(Mockito.any())).thenReturn(student);
		// Then
		Student savedCourse = studentService.createStudent(new Student());
		// AND
		assertThat(savedCourse.getName()).isEqualTo(student.getName());
	}

	@Test
	void Should_UpdateStudent_When_ProvideCourse() {
		// Given
		Student student = new Student(1, "student Name", new HashSet<>());
		Course course = new Course(1, "course 1", new HashSet<>());
		student.getCourses().add(course);
		// When
		Mockito.when(studentRepo.save(Mockito.any())).thenReturn(student);
		Mockito.when(studentRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
		// Then
		Student updatedCourse = studentService.updateStudent(STUDENT_ID , new Student());
		// AND
		assertThat(updatedCourse.getName()).isEqualTo(student.getName());
	}

	@Test
	void Should_DeleteStudent_When_ProvideCourse() {
		// When
		Mockito.doNothing().when(studentRepo).deleteById(Mockito.anyInt());
		Mockito.when(studentRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		// Then
		studentService.deleteStudentById(STUDENT_ID);
		// AND
		assertThat(studentRepo.findById(STUDENT_ID)).isEmpty();
	}

}
