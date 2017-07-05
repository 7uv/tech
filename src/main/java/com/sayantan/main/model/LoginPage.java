/*
 *
 * Copyright 2016, Sayantan Ghosh, and/or his affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This class is to be used for maintaining user registration.
 */
package com.sayantan.main.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(name = "credentials", uniqueConstraints = @UniqueConstraint(columnNames = "userid"))
// Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name",
// "company_id"}))
public class LoginPage implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 6, message="User ID must be more than 6 characters")
	@Column(name = "userid")
	private String userid;

	@NotNull
	@NotEmpty
	@Size(min = 6, message="Password must be minimum 6 characters")
	//Pattern(regexp = "(^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,})", message="Password must be minimum 8 characters at least 1 Uppercase Alphabet, 1 Lowercase Alphabet, 1 Number and 1 Special Character")
	@Column(name = "password")
	private String password;
	
	@Transient
	private int valP;
	
	@OneToOne
	private Member member;
	
	public LoginPage(Long id, String userid, String password){
		 super();
		 this.id = id;
	     this.userid = userid;
	     this.password = password;
	}
	
	public LoginPage(){
		super();
	}

	public Long getId() {
		return id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValP() {
		return valP;
	}

	public void setValP(int valP) {
		this.valP = valP;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
