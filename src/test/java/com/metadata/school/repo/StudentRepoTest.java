package com.metadata.school.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.metadata.school.entity.Student;

@ExtendWith(MockitoExtension.class)
class StudentRepoTest {
	
	private final static Integer COURSE_ID = 1;

	private @Mock StudentRepo studentRepo;

	@Test
	void whenFindById_thenReturnCourse() {
		// Given
		Student course = new Student(1, "course 1", new HashSet<>());
		Mockito.when(studentRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(course));
		// when
		Student found = studentRepo.findById(course.getId()).get();

		// then
		assertThat(found.getName()).isEqualTo(course.getName());
	}

	@Test
	void whenCreateCourse_thenReturn_CreatedCourse() {
		// Given
		Student student = new Student(1, "student 1", new HashSet<>());
		Mockito.when(studentRepo.save(Mockito.any())).thenReturn(student);
		// when
		Student savedStudent = studentRepo.save(student);
		// then
		assertThat(savedStudent.getName()).isEqualTo(student.getName());

	}
	
	@Test
	void whenUpdateCourse_thenReturn_UpdatedCourse() {
		// Given
		Student student = new Student(1, "course 1", new HashSet<>());
		Mockito.when(studentRepo.save(Mockito.any())).thenReturn(student);
		// when
		Student updatedStudent = studentRepo.save(student);
		// then
		assertThat(updatedStudent.getName()).isEqualTo(student.getName());

	}
	@Test
	 void whenDeleteCourse_then_DeleteTheCourse() {
		// when
		Mockito.doNothing().when(studentRepo).deleteById(Mockito.anyInt());
		Mockito.when(studentRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		// Then
		studentRepo.deleteById(COURSE_ID);
		// then
		assertThat(studentRepo.findById(COURSE_ID)).isEmpty();
		
	}

}
