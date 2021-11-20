//package com.metadata.school.mapper;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//
//import org.modelmapper.ModelMapper;
//import org.modelmapper.PropertyMap;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.metadata.school.dto.StudentDTO;
//import com.metadata.school.entity.Student;
//
//@Component
//public class StudentMappers {
//	
//	 private @Autowired ModelMapper modelMapper;
//		
//	 public StudentDTO convertToDto(Student student) {
//			StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
//			
//		    return studentDTO;
//		}
//		public List<StudentDTO>  convertListToDto(List<Student> students) {
//			List<StudentDTO> list = new ArrayList<>();
//			students.forEach(student -> {
//				StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
//				list.add(studentDTO);
//			});
//		    return list;
//		}
//		
//		public Student convertFromDto(StudentDTO studentDTO) {
//			return modelMapper.map(studentDTO, Student.class);
//		}
//		
//		public List<StudentDTO> convertFromDTOListWithoutCourses(List<Student> students) {
//			List<StudentDTO> list = new ArrayList<>();
//			modelMapper.addMappings(skipCoursesSet2);
//			students.forEach(student -> {
//				StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
//				list.add(studentDTO);
//			});
//		    return list;
//		}
//		public StudentDTO convertFromDtoWithoutCourses(Student student) {
//			return modelMapper.addMappings(skipCoursesSet2).map(student);
//		}
//		
//		 PropertyMap<StudentDTO, Student> skipCoursesSet = new PropertyMap<StudentDTO, Student>() {
//		      protected void configure() {
//		         skip().setCources(null);
//		     }
//		   };
//		   
//		   PropertyMap<Student, StudentDTO> skipCoursesSet2 = new PropertyMap<Student, StudentDTO>() {
//			      protected void configure() {
//			         skip().setCources(null);
//			     }
//			   };
//
//}
