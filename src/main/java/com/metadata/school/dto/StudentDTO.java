package com.metadata.school.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.metadata.school.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(exclude = { "courses"})
@EqualsAndHashCode(exclude = { "courses"})
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO implements Serializable{
	private static final long serialVersionUID = 4821147910505879310L;
	
	private Integer id;
	private String name;
	@JsonInclude(Include.NON_EMPTY)
	private Set<CourseDTO> courses = new HashSet<>();

}
