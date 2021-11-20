package com.metadata.school.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metadata.school.annotation.IsAdmin;
import com.metadata.school.dto.CourseDTO;
import com.metadata.school.dto.StudentDTO;

@IsAdmin
@RequestMapping("/v1/admin/report")
public interface ReportAPI {
	
	
	@GetMapping("/filter-students-by-course-id/{courseId}")
	public ResponseEntity<List<StudentDTO>> getStudentsByCourseId(@PathVariable Integer courseId);
	
	@GetMapping("/filter-courses-by-student-id/{studentId}")
	public ResponseEntity<List<CourseDTO>> getCoursesByStudentId(@PathVariable Integer studentId);
	
	@GetMapping("/filter-students-by-course-id-without-courses/{courseId}")
	public ResponseEntity<List<StudentDTO>> getStudentsByCourseIdWithouCourses(@PathVariable Integer courseId);
	
	@GetMapping("/filter-courses-by-student-id-without-student/{studentId}")
	public ResponseEntity<List<CourseDTO>> getCoursesByStudentIdWithoutStudents(@PathVariable Integer studentId);
}
