package com.metadata.school.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.metadata.school.dto.CourseDTO;
import com.metadata.school.dto.StudentDTO;
import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;

 class CourseMapperTest {
	
	private CourseMapper mapper = Mappers.getMapper(CourseMapper.class);
	
	   @Test
	     void givenCourseSourceToCourseDTO_whenMaps_thenCorrect() {
		   // Given
		 
		   Course source= new Course( 1 ,"Course 1" , new HashSet<>());
		   Student student= new Student( 1 ,"Student 1" , new HashSet<>());
		   source.getStudents().add(student);
		   // When
		   CourseDTO target = mapper.convertToDto(source);
		   // Then
		   assertEquals(source.getId(), target.getId());
		   assertEquals(source.getName(), target.getName());
		   assertEquals(source.getStudents().size(), target.getStudents().size());
		   
	   }

}
