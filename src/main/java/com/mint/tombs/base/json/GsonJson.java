/**   
 * Copyright © 2021 mint. All rights reserved.
 * 
 * @Title: GsonJson.java 
 * @Prject: tombs
 * @Package: com.mint.tombs.base.json 
 * @Description: 使用gson操作json数据 特点：功能强大
 * @author: mint
 * @date: 2021年7月23日 下午4:13:02 
 * @version: V1.0   
 */
package com.mint.tombs.base.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mint.tombs.base.json.pojo.Student;

/**
 * @ClassName: GsonJson
 * @Description: 使用Gson操作json
 * @author: mint
 * @date: 2021年7月23日 下午4:13:02
 */
public class GsonJson {

	public static void main(String[] args) throws FileNotFoundException {
		readJsonToObj();
	}

	/**
	 * 
	 * @Title: readJsonToObj 
	 * @Description: 从文件中读取信息并放到object
	 * @throws FileNotFoundException
	 * @return: void
	 */
	public static void readJsonToObj() throws FileNotFoundException {
		File file = new File("src/main/resources/base/json/students2.json");
		FileReader reader = new FileReader(file);

		Gson gson = new Gson();
		List<Student> students = gson.fromJson(reader, new TypeToken<List<Student>>() {
		}.getType());

		// 循环遍历信息
		for (Student student : students) {
			System.out.println(student);
		}

	}

}
