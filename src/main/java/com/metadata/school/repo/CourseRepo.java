package com.metadata.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metadata.school.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{

}
