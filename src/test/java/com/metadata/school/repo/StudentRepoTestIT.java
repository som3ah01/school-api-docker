package com.metadata.school.repo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.metadata.school.entity.Student;

@DataJpaTest
 class StudentRepoTestIT {

	private @Autowired StudentRepo studentRepo;
	// Some data as inserted in SQL file INTO H2QL DB for Integration Test
	private final static Integer STUDENT_ID = 1;
	private final static String STUDENT_NAME = "student 1";

	@Test
	 void whenFindById_thenReturn_Course() {
		// Given
		Student found = studentRepo.findById(STUDENT_ID).get();
		// then
		assertThat(found.getName()).isEqualTo(STUDENT_NAME);
	}
	@Test
	 void whenCreateCourse_thenReturn_CreatedCourse() {
		// Given
		Student student = new Student(null, "student Test", null);
		// when
		Student savedStudent = studentRepo.save(student);
		// then
		assertThat(savedStudent.getName()).isEqualTo(student.getName());
		
	}
	
	@Test
	 void whenUpdateCourse_thenReturn_UpdatedCourse() {
		// Given
		Student student = new Student(STUDENT_ID, "course Test", null);
		// when
		Student savedStudent = studentRepo.save(student);
		// then
		assertThat(savedStudent.getName()).isEqualTo(student.getName());
		
	}
	@Test
	 void whenDeleteCourse_then_DeleteTheCourse() {
		// when
		studentRepo.deleteById(STUDENT_ID);
		// then
		assertThat(studentRepo.findById(STUDENT_ID).isEmpty());
		
	}

}
