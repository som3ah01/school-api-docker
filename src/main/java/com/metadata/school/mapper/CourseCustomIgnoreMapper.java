package com.metadata.school.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.metadata.school.dto.CourseDTO;
import com.metadata.school.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseCustomIgnoreMapper extends Serializable {

	CourseCustomIgnoreMapper INSTANCE = Mappers.getMapper( CourseCustomIgnoreMapper.class ); 
	
	@Mapping(target = "students", ignore = true)
	List<CourseDTO> convertToDTOListWithoutStudents(List<Course> courses);
	
	@Mapping(target = "students", ignore = true)
	CourseDTO convertToDTOWithoutCourses(Course courseDTO);

}
