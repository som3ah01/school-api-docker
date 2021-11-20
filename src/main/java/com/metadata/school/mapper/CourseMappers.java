//package com.metadata.school.mapper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.metadata.school.dto.CourseDTO;
//import com.metadata.school.entity.Course;
//
//@Component
//public class CourseMappers {
//	
//    private @Autowired ModelMapper modelMapper;
//	
//	public CourseDTO convertToDto(Course course) {
//		return modelMapper.map(course, CourseDTO.class);
//	}
//	public List<CourseDTO>  convertListToDto(List<Course> courses) {
//		List<CourseDTO> list = new ArrayList<>();
//		courses.forEach(course -> {
//			CourseDTO courseDTO = modelMapper.map(course, CourseDTO.class);
//			list.add(courseDTO);
//		});
//	    return list;
//	}
//	
//	public Course convertFromDto(CourseDTO courseDTO) {
//		return modelMapper.map(courseDTO, Course.class);
//	}
//
//}
