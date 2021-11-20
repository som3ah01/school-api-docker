package com.metadata.school.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;
import com.metadata.school.exceptions.OverSizeException;
import com.metadata.school.repo.CourseRepo;
import com.metadata.school.repo.StudentRepo;
import com.metadata.school.service.impl.RegistertionCourseServiceImpl;

@ExtendWith(SpringExtension.class)
class RegistertionCourseServiceTest {
	
	private static final Integer STUDENT_ID = 1;
	private static final Integer COURSE_ID = 1;
	
	private @InjectMocks RegistertionCourseServiceImpl registertionCourseService;
	private @Mock StudentRepo studentRepo;
	private @Mock CourseRepo courseRepo;
	
	@Test
	void Should_ReturnStudentWithCourses_When_RegisterToCourse() {
		// Given
		Student student = new Student(1, "student Name", new HashSet<>());
		student.getCourses().add(new Course(1, "course 1", new HashSet<>()));
		for (int i = 1; i < 5; i++) {
			student.getCourses().add(new Course(i, "course " +i, new HashSet<>()));
		}
		Course course = new Course(1, "course 1", new HashSet<>());
		
		// When
		Mockito.when(studentRepo.save(Mockito.any())).thenReturn(student);
		Mockito.when(studentRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
		Mockito.when(courseRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(course));
		// Then
		Student updatedCourse = registertionCourseService.registerStudenttoCourse(STUDENT_ID, COURSE_ID);
		// AND
		assertThat(updatedCourse.getCourses().size()).isLessThanOrEqualTo(5);
		assertThat(course.getStudents().size()).isLessThanOrEqualTo(50);
	}
	
	@Test
	void Should_ThrowOverSizeException_When_RegisterToCourseAndStudentHas5Courses() {
		// Given
		Student student = new Student(1, "student Name", new HashSet<>());
		for (int i = 1; i < 6; i++) {
			student.getCourses().add(new Course(i, "course " +i, new HashSet<>()));
		}
		Course course = new Course(1, "course 1", new HashSet<>());
		// When
		Mockito.when(studentRepo.save(Mockito.any())).thenReturn(student);
		Mockito.when(studentRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
		Mockito.when(courseRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(course));
		// Then
		Assertions.assertThrows(OverSizeException.class, () -> {
			registertionCourseService.registerStudenttoCourse(STUDENT_ID, COURSE_ID);
		});
	}
	@Test
	void Should_ThrowOverSizeException_When_RegisterToCourseAndCourseHas50Students() {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		for (int i = 1; i < 60; i++) {
			course.getStudents().add(new Student(i, "studemnt " +i, new HashSet<>()));
		}
		Student student = new Student(1, "student Name", new HashSet<>());
		student.getCourses().add(course);
		// When
		Mockito.when(studentRepo.save(Mockito.any())).thenReturn(student);
		Mockito.when(studentRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
		Mockito.when(courseRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(course));
		// Then
		Assertions.assertThrows(OverSizeException.class, () -> {
			registertionCourseService.registerStudenttoCourse(STUDENT_ID, COURSE_ID);
		});
	}
}
