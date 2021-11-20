package com.metadata.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;
import com.metadata.school.exceptions.OverSizeException;
import com.metadata.school.exceptions.ResourceNotFoundException;
import com.metadata.school.repo.CourseRepo;
import com.metadata.school.repo.StudentRepo;
import com.metadata.school.service.RegistertionCourseService;
import com.metadata.school.util.GlobalConstants;
import com.metadata.school.util.GlobalEnums;

@Service
public class RegistertionCourseServiceImpl implements RegistertionCourseService {

	private @Autowired CourseRepo courseRepo;
	private @Autowired StudentRepo studentRepo;

	@Override
	public Student registerStudenttoCourse(Integer studentId, Integer courseId) {
		Assert.notNull(studentId, String.format(GlobalConstants.MISSING_MANDATORY_VALUE_MSG, "studentId"));
		Assert.notNull(courseId, String.format(GlobalConstants.MISSING_MANDATORY_VALUE_MSG, "courseId"));
		Student student = studentRepo.findById(studentId).orElseThrow(
				() -> new ResourceNotFoundException(String.format(GlobalConstants.STUDENT_NOT_FOUND, studentId),
						GlobalEnums.Errors.RESOURCE_NOT_FUND.toString()));
		Course course = courseRepo.findById(courseId).orElseThrow(
				() -> new ResourceNotFoundException(String.format(GlobalConstants.COURSE_NOT_FOUND, courseId),
						GlobalEnums.Errors.RESOURCE_NOT_FUND.toString()));
		if (student.getCourses().size() >= 5) {
			throw new OverSizeException(
					String.format(GlobalConstants.STUDENT_HAS_MAX_LIMIT, student.getCourses().size()),
					GlobalEnums.Errors.HAS_MAX_LIMIT.toString());
		}
		if (course.getStudents().size() >= 50) {
			throw new OverSizeException(
					String.format(GlobalConstants.COURSE_HAS_MAX_LIMIT, course.getStudents().size()),
					GlobalEnums.Errors.HAS_MAX_LIMIT.toString());
		}
		student.getCourses().add(course);
		return studentRepo.save(student);

	}

}
