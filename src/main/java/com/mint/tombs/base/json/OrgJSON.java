/**   
 * Copyright © 2021 mint. All rights reserved.
 * 
 * @Title: OrgJSON.java 
 * @Prject: tombs
 * @Package: com.mint.tombs.base.json 
 * @Description: 使用org.json处理json数据
 *               org.json特点：简单易用，缺点是无法处理负载应用
 *               
 * @author: Administrator   
 * @date: 2021年7月23日 下午2:27:59 
 * @version: V1.0   
 */
package com.mint.tombs.base.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mint.tombs.base.json.pojo.Student;

/**
 * @ClassName: OrgJSON
 * @Description: 使用org.json处理json数据
 * @author: mint
 * @date: 2021年7月23日 下午2:27:59
 */
public class OrgJSON {

	public static void main(String[] args) throws JSONException, FileNotFoundException, IOException {

		// 结果放入jsonObject
		// putValueInJson();

		// 从文件读取数据并输出
		readJsonFileToPojo();
	}

	/**
	 * 
	 * @Title: putValueInJson
	 * @Description: 将结果放入jsonObject
	 * @return: void
	 * @throws JSONException
	 */
	public static void putValueInJson() throws JSONException {
		JSONObject obj = new JSONObject();

		int[] score = new int[] { 1, 45, 978, 23 };
		obj.put("name", "张三");
		obj.put("age", "李四");
		obj.put("score", score);

		System.out.println(obj.toString());
	}

	public static void readJsonFileToPojo() throws FileNotFoundException, IOException, JSONException {
		File file = new File("src/main/resources/base/json/students.json");
		// 读取文件，并放入json
		try (FileReader reader = new FileReader(file)) {
			int length = (int) file.length();
			char[] chars = new char[length];
			reader.read(chars);
			String s = String.valueOf(chars);

			// 将读出来的json转换为数组
			JSONObject obj = new JSONObject(s);
			List<Student> students = new ArrayList<Student>();
			JSONArray stus = obj.getJSONArray("students");

			// 循环遍历当前数组，将结果存入list集合
			for (int i = 0; i < stus.length(); i++) {
				JSONObject stuo = stus.getJSONObject(i);
				Student sinfo = new Student();

				/**
				 * 由于此处操作比较繁琐，因此大部分程序员不倾向使用org.json
				 */
				sinfo.setName(stuo.getString("name"));
				sinfo.setSex(stuo.getString("sex"));
				sinfo.setScore(stuo.getString("score"));

				students.add(sinfo);
			}

			// 遍历输出student信息
			for (Student student : students) {
				System.out.println(student.toString());
			}

		}

	}

}
