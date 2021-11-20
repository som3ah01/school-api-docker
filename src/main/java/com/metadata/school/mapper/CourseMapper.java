package com.metadata.school.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.metadata.school.dto.CourseDTO;
import com.metadata.school.dto.StudentDTO;
import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;

@Mapper(componentModel = "spring")
public interface CourseMapper extends Serializable{

	CourseMapper INSTANCE = Mappers.getMapper( CourseMapper.class ); 
	
	CourseDTO convertToDto(Course course);
	Course convertFromDto(CourseDTO courseDTO);
	List<CourseDTO> convertListToDto(List<Course> courses);
	@Mapping(target = "courses", ignore = true)
	StudentDTO studentToStudentDTO(Student student);
	
	

}
