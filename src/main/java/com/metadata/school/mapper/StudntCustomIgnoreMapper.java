package com.metadata.school.mapper;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.metadata.school.dto.StudentDTO;
import com.metadata.school.entity.Student;

@Mapper(componentModel = "spring")
public interface StudntCustomIgnoreMapper extends Serializable{
	
	StudntCustomIgnoreMapper INSTANCE = Mappers.getMapper( StudntCustomIgnoreMapper.class ); 
	
	@Mapping(target = "courses", ignore = true)
	StudentDTO convertToDTOWithoutCourses(Student student);
	
	@Mapping(target = "cources", ignore = true)
	 List<StudentDTO> convertToDTOListWithoutCourses(List<Student> students);

}
