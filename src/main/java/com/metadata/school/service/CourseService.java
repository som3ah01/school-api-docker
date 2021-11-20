package com.metadata.school.service;

import java.util.List;

import com.metadata.school.entity.Course;

public interface CourseService {

	public Course getCourseById(Integer courseId);

	public List<Course> getAllCourses();

	public Course updateCourse(Integer courseId, Course course);

	public Course createCourse(Course course);

	public void deleteCourseById(Integer courseId);

}