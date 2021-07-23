/**   
 * Copyright © 2021 mint. All rights reserved.
 * 
 * @Title: Student.java 
 * @Prject: tombs
 * @Package: com.mint.tombs.base.json.pojo 
 * @Description: json实体类
 * @author: mint   
 * @date: 2021年7月23日 下午2:31:49 
 * @version: V1.0   
 */
package com.mint.tombs.base.json.pojo;

import java.util.List;

/**
 * @ClassName: Student
 * @Description: Student实体类
 * @author: Administrator
 * @date: 2021年7月23日 下午2:31:49
 */
public class Student {
	private String name;
	private String sex;
	private String score;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: toString
	 * 
	 * @Description: TODO
	 * 
	 * @return
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + "-->>" + this.sex + "-->>" + this.score;
	}

}
