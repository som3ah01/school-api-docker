package com.metadata.school.api;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;

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

import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = { ReportAPI.class })
//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
 class ReportAPITestIT {
	
	private static final Integer COURSE_ID = 1;
	private static final Integer STUDENT_ID = 1;
	private static final String BASE_URL = "/v1/admin/report";

	@Autowired
	private MockMvc mockMvc;

//	private @MockBean CourseService courseService;
//	private @MockBean CourseMapper courseMapper;
//	private @MockBean RegistertionCourseService registertionCourseService;
//	private @MockBean StudentService studentService;
//	private @MockBean StudentMapper studentMapper;
//	private @MockBean CourseCustomIgnoreMapper courseCustomIgnoreMapper;
//	private @MockBean StudntCustomIgnoreMapper studntCustomIgnoreMapper;
//	private @MockBean ApiUserDetailsService apiUserDetailsService;
	
	@Test
	@WithMockUser(username="admin",password = "admin", roles={"ADMIN"})
	void testSucssesGetStudetsIfProvidedCourseID() throws Exception {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		Student student = new Student(1, "student Name", new HashSet<>());
		course.getStudents().add(student);
		// When
//		Mockito.when(courseService.getCourseById(Mockito.anyInt())).thenReturn(course);
		String url = BASE_URL + "/filter-students-by-course-id/" + COURSE_ID;
		// Then
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].id").isNotEmpty())
		.andExpect(jsonPath("$.[0].courses").isNotEmpty())
	      .andReturn();
	}
	@Test
	@WithMockUser(username="user",password = "user", roles={"USER"})
	void testFailGetStudetsIfProvidedCourseID() throws Exception {
		// Given
		Course course = new Course(1, "course 1", new HashSet<>());
		Student student = new Student(1, "student Name", new HashSet<>());
		course.getStudents().add(student);
		// When
//		Mockito.when(courseService.getCourseById(Mockito.anyInt())).thenReturn(course);
		String url = BASE_URL + "/filter-students-by-course-id/" + COURSE_ID;
		// Then
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder).andDo(print()).andExpect(status().isForbidden())
	      .andReturn();
	}
	@Test
	@WithMockUser(username="admin",password = "admin", roles={"ADMIN"})
	void testSucssesGetCoursesIfProvidedStudentID() throws Exception {
		// Given
		String url = BASE_URL + "/filter-courses-by-student-id/" + STUDENT_ID;
		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		// Then
		mockMvc.perform(builder).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].id").isNotEmpty())
		.andExpect(jsonPath("$.[0].students").isNotEmpty())
	      .andReturn();
	}
	@Test
	@WithMockUser(username="user",password = "user", roles={"USER"})
	void testFailGetCoursesIfProvidedStudentID() throws Exception {
		// Given
		String url = BASE_URL + "/filter-courses-by-student-id/" + STUDENT_ID;
		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		// Then
		mockMvc.perform(builder).andDo(print()).andExpect(status().isForbidden())
	      .andReturn();
	}
	@Test
	@WithMockUser(username="admin",password = "admin", roles={"ADMIN"})
	void testSucssesGetStudetsWithoutCoursesIfProvidedCourseID() throws Exception {
		// Given
		String url = BASE_URL + "/filter-students-by-course-id-without-courses/" + COURSE_ID;
		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		// Then
		mockMvc.perform(builder).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].id").isNotEmpty())
		.andExpect(jsonPath("$.[0].courses").doesNotExist())
	      .andReturn();
	}
	@Test
	@WithMockUser(username="user",password = "user", roles={"USER"})
	void testFailGetStudetsWithoutCoursesIfProvidedCourseID() throws Exception {
		// Given
		String url = BASE_URL + "/filter-students-by-course-id-without-courses/" + COURSE_ID;
		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		// Then
		mockMvc.perform(builder).andDo(print()).andExpect(status().isForbidden())
	      .andReturn();
	}
	@Test
	@WithMockUser(username="admin",password = "admin", roles={"ADMIN"})
	void testSucssesGetCoursesWithoutStudentsIfProvidedStudentID() throws Exception {
		// Given
		String url = BASE_URL + "/filter-courses-by-student-id-without-student/" + STUDENT_ID;
		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		// Then
		 mockMvc.perform(builder).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].id").isNotEmpty())
		.andExpect(jsonPath("$.[0].students").doesNotExist())
	      .andReturn();
		
	}
	@Test
	@WithMockUser(username="user",password = "user", roles={"USER"})
	void testFailGetCoursesWithoutStudentsIfProvidedStudentID() throws Exception {
		// Given
		String url = BASE_URL + "/filter-courses-by-student-id-without-student/" + STUDENT_ID;
		// When
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(url)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON);
		// Then
		mockMvc.perform(builder).andDo(print()).andExpect(status().isForbidden())
	      .andReturn();
	}

}
