package com.metadata.school.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.metadata.school.entity.Course;
import com.metadata.school.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(exclude = { "students"})
@EqualsAndHashCode(exclude = { "students"})
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO implements Serializable {
	private static final long serialVersionUID = -4063511625426239664L;
	private  Integer id;
	private String name;
	@JsonInclude(Include.NON_EMPTY)
	private Set<StudentDTO> students= new HashSet<>();
}
