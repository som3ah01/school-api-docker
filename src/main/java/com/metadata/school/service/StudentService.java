package com.metadata.school.service;

import java.util.List;

import com.metadata.school.entity.Student;

public interface StudentService {

	public Student getStudentById(Integer studentId);

	public List<Student> getAllStudent();

	public Student updateStudent(Integer studentId, Student student);

	public Student createStudent(Student student);

	public void deleteStudentById(Integer studentId);

}