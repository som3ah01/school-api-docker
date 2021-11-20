package com.metadata.school.api;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
 class RegistertionCourseAPITestIT {
	
	private static final Integer COURSE_ID = 1;
	private static final Integer LIMITED_STUDENT_ID = 1;
	private static final Integer OK_STUDENT_ID = 5;
	private static final String BASE_URL = "/v1/register";
	
	private @Autowired MockMvc mockMvc;
	
	@Test
	@WithMockUser(username="admin",password = "admin", roles={"ADMIN"})
	void testSucssesRegisterStudenttoCourseIfProvidedStudentIDAndCourseID() throws Exception {
		// Given
		String url = BASE_URL + "/student/"+OK_STUDENT_ID+"/to/course/" + COURSE_ID;
		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		// Then
		mockMvc.perform(builder).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").isNotEmpty())
		.andExpect(jsonPath("$.courses").isNotEmpty())
	      .andReturn();
	}
	@Test
	@WithMockUser(username="user",password = "user", roles={"USER"})
	void testFailRegisterStudenttoCourseIfProvidedStudentIDAndCourseID() throws Exception {
		// Given
		String url = BASE_URL + "/student/"+LIMITED_STUDENT_ID+"/to/course/" + COURSE_ID;
		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		// Then
		mockMvc.perform(builder).andDo(print()).andExpect(status().isBandwidthLimitExceeded());
	}

}
