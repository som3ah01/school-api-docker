package com.metadata.school.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.metadata.school.api.ReportAPI;
import com.metadata.school.dto.CourseDTO;
import com.metadata.school.dto.StudentDTO;
import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;
import com.metadata.school.mapper.CourseCustomIgnoreMapper;
import com.metadata.school.mapper.CourseMapper;
import com.metadata.school.mapper.StudentMapper;
import com.metadata.school.mapper.StudntCustomIgnoreMapper;
import com.metadata.school.service.CourseService;
import com.metadata.school.service.StudentService;

@RestController
public class ReportController implements ReportAPI {

	
	private @Autowired StudentService studentService;
	private @Autowired CourseService courseService;
	private @Autowired StudentMapper studentMapper;
	private @Autowired CourseMapper courseMapper;
	private @Autowired CourseCustomIgnoreMapper courseCustomIgnoreMapper;
	private @Autowired StudntCustomIgnoreMapper studntCustomIgnoreMapper;

	
	@Override
	public ResponseEntity<List<StudentDTO>> getStudentsByCourseId(Integer courseId) {
		List<Student> students = new ArrayList<>(courseService.getCourseById(courseId).getStudents());
		return new ResponseEntity<>(studentMapper.convertListToDto(students), HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<CourseDTO>> getCoursesByStudentId(Integer studentId) {
		Set<Course> coursesSet = studentService.getStudentById(studentId).getCourses();
		List<Course> courses = coursesSet.stream().collect(Collectors.toList());
		return new ResponseEntity<>(courseMapper.convertListToDto(courses), HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<StudentDTO>> getStudentsByCourseIdWithouCourses(Integer courseId) {
		Set<Student> studentsSet = courseService.getCourseById(courseId).getStudents();
		List<Student> students = studentsSet.stream().collect(Collectors.toList());
		return new ResponseEntity<>(studntCustomIgnoreMapper.convertToDTOListWithoutCourses(students), HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<CourseDTO>> getCoursesByStudentIdWithoutStudents(Integer studentId) {
		Set<Course> coursesSet = studentService.getStudentById(studentId).getCourses();
		List<Course> courses = coursesSet.stream().collect(Collectors.toList());
		return new ResponseEntity<>(courseCustomIgnoreMapper.convertToDTOListWithoutStudents(courses), HttpStatus.OK);
	}

}
