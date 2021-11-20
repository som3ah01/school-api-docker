package com.metadata.school.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.metadata.school.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 class CourseRepoTestIT {

	private @Autowired CourseRepo courseRepo;
	// Some data as inserted in SQL file INTO H2QL DB for Integration Test
	private final static Integer COURSE_ID = 1;
	private final static String COURSE_NAME = "course 1";

	@Test
	 void whenFindById_thenReturn_Course() {
		// Given
	   
		Course course = new Course(1, "course 1", new HashSet<>());
//		Student student = new Student(1, "Student 1", new HashSet<>());
//		course.getStudents().add(student);
//		entityManager.persist(course);
//		entityManager.flush();
		
//		 Mockito.when(courseRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(course));

		// when
		Course found = courseRepo.findById(COURSE_ID).get();

		// then
		assertThat(found.getName()).isEqualTo(COURSE_NAME);
	}
	@Test
	 void whenCreateCourse_thenReturn_CreatedCourse() {
		// Given
		Course course = new Course(null, "course Test", null);
		// when
		Course savedCourse = courseRepo.save(course);
		// then
		assertThat(savedCourse.getName()).isEqualTo(course.getName());
		
	}
	
	@Test
	 void whenUpdateCourse_thenReturn_UpdatedCourse() {
		// Given
		Course course = new Course(COURSE_ID, "course Test", null);
		// when
		Course savedCourse = courseRepo.save(course);
		// then
		assertThat(savedCourse.getName()).isEqualTo(course.getName());
		
	}
	@Test
	 void whenDeleteCourse_then_DeleteTheCourse() {
		// when
		courseRepo.deleteById(COURSE_ID);
		// then
		assertThat(courseRepo.findById(COURSE_ID).isEmpty());
		
	}

}
