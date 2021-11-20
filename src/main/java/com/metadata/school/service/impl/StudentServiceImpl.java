package com.metadata.school.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.metadata.school.entity.Student;
import com.metadata.school.exceptions.ResourceNotFoundException;
import com.metadata.school.repo.StudentRepo;
import com.metadata.school.service.StudentService;
import com.metadata.school.util.GlobalConstants;
import com.metadata.school.util.GlobalEnums;

@Service
public class StudentServiceImpl implements Serializable, StudentService {
	private static final long serialVersionUID = 8384654638170616524L;

	private @Autowired StudentRepo studentRepo;

	@Override
	public Student getStudentById(Integer studentId) {
		Assert.notNull(studentId, String.format(GlobalConstants.MISSING_MANDATORY_VALUE_MSG, "studentId"));
		return studentRepo.findById(studentId).orElseThrow(
				() -> new ResourceNotFoundException(String.format(GlobalConstants.STUDENT_NOT_FOUND, studentId),
						GlobalEnums.Errors.RESOURCE_NOT_FUND.toString()));
	}

	@Override
	public List<Student> getAllStudent() {
		return studentRepo.findAll();
	}

	@Override
	public Student updateStudent(Integer studentId, Student student) {
		Assert.notNull(studentId, String.format(GlobalConstants.MISSING_MANDATORY_VALUE_MSG, "studentId"));
		studentRepo.findById(studentId).orElseThrow(
				() -> new ResourceNotFoundException(String.format(GlobalConstants.STUDENT_NOT_FOUND, studentId),
						GlobalEnums.Errors.RESOURCE_NOT_FUND.toString()));
		student.setId(studentId);
		return studentRepo.save(student);
	}

	@Override
	public Student createStudent(Student student) {
		return studentRepo.save(student);
	}

	@Override
	public void deleteStudentById(Integer studentId) {
		Assert.notNull(studentId, String.format(GlobalConstants.MISSING_MANDATORY_VALUE_MSG, "studentId"));
		studentRepo.deleteById(studentId);
	}

}
