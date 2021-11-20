package com.metadata.school.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@EqualsAndHashCode(exclude = { "courses"})
@ToString(exclude = { "courses"})
@AllArgsConstructor
@NoArgsConstructor
public class Student  implements Serializable {
	
	private static final long serialVersionUID = 3491970212025928120L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native") 
	private Integer id;
	private String name;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	  name = "course_regestertion", 
	  joinColumns = @JoinColumn(name = "student_id"), 
	  inverseJoinColumns = @JoinColumn(name = "course_id"))
	 private Set<Course> courses = new HashSet<>();
	

}
