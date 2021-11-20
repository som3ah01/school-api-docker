package com.metadata.school.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.metadata.school.dto.StudentDTO;
import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;

 class StudentMapperTest {
	
	private StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
	
	   @Test
	     void givenStudentSourceToStudentDTO_whenMaps_thenCorrect() {
		   // Given
		   Student source= new Student( 1 ,"Student 1" , new HashSet<>());
		   Course course= new Course( 1 ,"Course 1" , new HashSet<>());
		   source.getCourses().add(course);
		   // When
		   StudentDTO target = mapper.convertToDto(source);
		   // Then
		   assertEquals(source.getId(), target.getId());
		   assertEquals(source.getName(), target.getName());
		   assertEquals(source.getCourses().size(), target.getCourses().size());
		   
	   }

}
