package com.jiujichina.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_user")
public class TbUser {
	
	@Column(name="F_USER_ID")
	public String id;
	
	
 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String name;
	
	public int age = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
