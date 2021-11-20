package com.metadata.school.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.metadata.school.dto.CourseDTO;
import com.metadata.school.dto.StudentDTO;
import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;

@Mapper(componentModel = "spring")

public interface StudentMapper extends Serializable {
	StudentMapper INSTANCE = Mappers.getMapper( StudentMapper.class ); 

	StudentDTO convertToDto(Student student);
	Student convertFromDto(StudentDTO studentDTO);
	List<StudentDTO> convertListToDto(List<Student> students);
	
	@Mapping(target = "students", ignore = true)
	CourseDTO courseToCourseDTO(Course course);
	
}
