package com.metadata.school.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.metadata.school.config.ApiUserDetailsService;
import com.metadata.school.controller.StudentController;
import com.metadata.school.entity.Student;
import com.metadata.school.mapper.StudentMapper;
import com.metadata.school.service.impl.StudentServiceImpl;


@WebMvcTest(StudentController.class)
class StudentAPITestIT {

	private static final Integer STUDENT_ID = 1;
	private static final String URL = "/v1/student/";

	@Autowired
	private MockMvc mockMvc;

	private @MockBean StudentServiceImpl studentService;
	private @MockBean StudentMapper studentMapper;
	private @MockBean ApiUserDetailsService apiUserDetailsService;
	


	@Test
	@WithMockUser(username="test",roles={"USER","ADMIN"})
	void testSucssesGetStudetIfProvidedID() throws Exception {
		// Given
		Student student = new Student(1, "Test Name", null);
		// When
		Mockito.when(studentService.getStudentById(Mockito.anyInt())).thenReturn(student);
		// Then
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(URL + STUDENT_ID).with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username="test",password = "test",roles={"ADMIN"})
	void testSucssesCreateStudetIfProvidedBody() throws Exception {
		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(URL).with(SecurityMockMvcRequestPostProcessors.csrf())
				.content("{\"name\": \"student 1.1\"}").contentType(MediaType.APPLICATION_JSON);
		// Then
		mockMvc.perform(builder).andExpect(status().isCreated());
	}

	@Test
	@WithMockUser(username="test",roles={"USER","ADMIN"})
	void testSucssesUpdateStudetIfProvidedIDAndBody() throws Exception {
		// Given
		Student student = new Student(1, "Test Name", null);
		// When
		Mockito.when(studentService.updateStudent(Mockito.anyInt(), Mockito.any())).thenReturn(student);
		// Then
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(URL + STUDENT_ID).with(SecurityMockMvcRequestPostProcessors.csrf())
				.content("{\"name\": \"student 6.1\"}").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username="test",password = "test",roles={"ADMIN"})
	void testSucssesDeleteStudetIfProvidedID() throws Exception {
		// When
		Mockito.doNothing().when(studentService).deleteStudentById(Mockito.anyInt());
		// Then
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(URL + STUDENT_ID).with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder).andExpect(status().isNoContent());
	}

	@Test
	@WithMockUser(username="test",password = "test",roles={"ADMIN"})
	void testFailDeleteStudetIfNoProvidedID() throws Exception {
		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(URL).with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder).andExpect(status().isMethodNotAllowed());
	}

}
