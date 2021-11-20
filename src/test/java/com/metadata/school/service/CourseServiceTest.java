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
import com.metadata.school.repo.CourseRepo;
import com.metadata.school.service.impl.CourseServiceImpl;

@ExtendWith(SpringExtension.class)
 class CourseServiceTest {

	private static final Integer COURSE_ID = 1;

	private @InjectMocks CourseServiceImpl courseService;
	private @Mock CourseRepo courseRepo;

	@Test
	void Should_ReturnCourse_When_ProvidesCourseId() {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		Student student = new Student(1, "student Name", new HashSet<>());
		course.getStudents().add(student);
		// When
		Mockito.when(courseRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(course));
		// Then
		Course found = courseService.getCourseById(COURSE_ID);
		// AND
		assertThat(found.getName()).isEqualTo(course.getName());
	}

	@Test
	void Should_CreateCourse_When_ProvideCourse() {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		Student student = new Student(1, "student Name", new HashSet<>());
		course.getStudents().add(student);
		// When
		Mockito.when(courseRepo.save(Mockito.any())).thenReturn(course);
		// Then
		Course savedCourse = courseService.createCourse(new Course());
		// AND
		assertThat(savedCourse.getName()).isEqualTo(course.getName());
	}

	@Test
	void Should_UpdateCourse_When_ProvideCourse() {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		Student student = new Student(1, "student Name", new HashSet<>());
		course.getStudents().add(student);
		// When
		Mockito.when(courseRepo.save(Mockito.any())).thenReturn(course);
		Mockito.when(courseRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(course));
		// Then
		Course updatedCourse = courseService.updateCourse(COURSE_ID , new Course());
		// AND
		assertThat(updatedCourse.getName()).isEqualTo(course.getName());
	}

	@Test
	void Should_DeleteCourse_When_ProvideCourse() {
		
		// When
		Mockito.doNothing().when(courseRepo).deleteById(Mockito.anyInt());
		Mockito.when(courseRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		// Then
		courseService.deleteCourseById(COURSE_ID);
		// AND
		assertThat(courseRepo.findById(COURSE_ID)).isEmpty();
	}

}
