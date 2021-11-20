package com.metadata.school.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;

@ExtendWith(MockitoExtension.class)
class CourseRepoTest {
	
	private final static Integer COURSE_ID = 1;

	private @Mock CourseRepo courseRepo;
//	CourseRepo courseRepo = Mockito.mock(CourseRepo.class);

	@Test
	void whenFindById_thenReturnCourse() {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		Mockito.when(courseRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(course));

		// when
		Course found = courseRepo.findById(course.getId()).get();

		// then
		assertThat(found.getName()).isEqualTo(course.getName());
	}

	@Test
	void whenCreateCourse_thenReturn_CreatedCourse() {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		Mockito.when(courseRepo.save(Mockito.any())).thenReturn(course);
		// when
		Course savedCourse = courseRepo.save(course);
		// then
		assertThat(savedCourse.getName()).isEqualTo(course.getName());

	}
	
	@Test
	void whenUpdateCourse_thenReturn_UpdatedCourse() {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		Mockito.when(courseRepo.save(Mockito.any())).thenReturn(course);
		// when
		Course updatedCourse = courseRepo.save(course);
		// then
		assertThat(updatedCourse.getName()).isEqualTo(course.getName());

	}
	@Test
	 void whenDeleteCourse_then_DeleteTheCourse() {
		// when
		Mockito.doNothing().when(courseRepo).deleteById(Mockito.anyInt());
		Mockito.when(courseRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		// Then
		courseRepo.deleteById(COURSE_ID);
		// then
		assertThat(courseRepo.findById(COURSE_ID)).isEmpty();
		
	}

}
