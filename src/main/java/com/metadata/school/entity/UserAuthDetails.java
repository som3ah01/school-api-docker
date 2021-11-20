package com.metadata.school.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_auth")
@Data
public class UserAuthDetails {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	@Column( name = "user_name" ,nullable = false, unique = true) 
	private String username;
	@Column(nullable = false) 
	private String password;
	private String role;
	private @Column( name = "is_active") Boolean isActive;

}
