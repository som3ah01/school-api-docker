package com.metadata.school.api;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.metadata.school.api.CourseAPI;
import com.metadata.school.config.ApiUserDetailsService;
import com.metadata.school.entity.Course;
import com.metadata.school.mapper.CourseMapper;
import com.metadata.school.repo.UserAuthDetailRepo;
import com.metadata.school.service.impl.CourseServiceImpl;


@WebMvcTest(controllers = { CourseAPI.class })
 class CourseAPITestIT {

	private static final Integer COURSE_ID = 1;
	private static final String URL = "/v1/course/";

	@Autowired
	private MockMvc mockMvc;

	private @MockBean CourseServiceImpl courseService;
	private @MockBean CourseMapper courseMapper;
	private @MockBean ApiUserDetailsService apiUserDetailsService;
	

    

	@Test
	@WithMockUser(username="test",roles={"USER","ADMIN"})
	void testSucssesGetCourseIfProvidedID() throws Exception {
		// Given
		Course course = new Course(1, "Test Name", null);
		// When
		Mockito.when(courseService.getCourseById(Mockito.anyInt())).thenReturn(course);

		// Then
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(URL + COURSE_ID)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username="test",password = "test",roles={"ADMIN"})
	void testSucssesCreateCourseIfProvidedBody() throws Exception {
		// Given
		Course course = new Course(1, "Test Name", null);
		// When
		Mockito.when(courseService.createCourse(Mockito.any())).thenReturn(course);

		// Then
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(URL).with(SecurityMockMvcRequestPostProcessors.csrf())
				.content("{\"name\": \"course 1.1\"}").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder).andExpect(status().isCreated());
	}

	@Test
	@WithMockUser(username="test",password = "test",roles={"ADMIN"})
	void testSucssesUpdateCourseIfProvidedIDAndBody() throws Exception {
		// Given
		Course course = new Course(1, "Test Name", null);
		// When
		Mockito.when(courseService.updateCourse(Mockito.anyInt(), Mockito.any())).thenReturn(course);

		// Then
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(URL + COURSE_ID).with(SecurityMockMvcRequestPostProcessors.csrf())
				.content("{\"name\": \"course 6.1\"}").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username="test",password = "test",roles={"ADMIN"})
	void testSucssesDeleteCourseIfProvidedID() throws Exception {
		// When
		Mockito.doNothing().when(courseService).deleteCourseById(Mockito.anyInt());

		// Then
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(URL + COURSE_ID).with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder).andExpect(status().isNoContent());
	}

	
	@Test
	@WithMockUser(username="test",password = "test",roles={"ADMIN"})
	void testFailDeleteCourseIfNoProvidedID() throws Exception {
		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(URL).with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder).andExpect(status().isMethodNotAllowed());
	}

}
