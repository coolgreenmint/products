/**   
 * Copyright © 2021 mint. All rights reserved.
 * 
 * @Title: JacksonJson.java 
 * @Prject: tombs
 * @Package: com.mint.tombs.base.json 
 * @Description: 使用JacksonJson操作json
 * @author: mint   
 * @date: 2021年7月23日 下午4:40:48 
 * @version: V1.0   
 */
package com.mint.tombs.base.json;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mint.tombs.base.json.pojo.Student;

/**
 * @ClassName: JacksonJson
 * @Description: 使用JacksonJson操作json
 * @author: mint
 * @date: 2021年7月23日 下午4:40:48
 */
public class JacksonJson {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		readFileToJson();
	}

	/**
	 * 
	 * @Title: readFileToJson
	 * @Description: 使用jackson方式读取json数据
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @return: void
	 */
	public static void readFileToJson() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/main/resources/base/json/students2.json");

		ObjectMapper om = new ObjectMapper();
		List<Student> students = om.readValue(file, new TypeReference<List<Student>>() {
		});

		for (Student student : students) {
			System.out.println(student);
		}

	}

}
