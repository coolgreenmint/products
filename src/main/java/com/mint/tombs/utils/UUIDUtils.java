package com.mint.tombs.utils;

import java.util.UUID;

/** 
 * @ClassName: UUIDUtils 
 * @Description: UUID 工具类
 * @author: mint
 * @date: 2021年7月19日 下午12:04:55  
 */
public class UUIDUtils {
	
	/**
	 * 
	 * @Title:  getUUIDWithLine
	 * @Description: 获取带横线的UUID
	 * @return 带横线的UUID
	 * @return: String
	 */
	public static String getUUIDWithLine() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 
	 * @Title: getUUIDWithOutLine 
	 * @Description: 获取不带横线的UUID 
	 * @return 不带横线的UUID
	 * @return: String
	 */
	public static String getUUIDWithOutLine() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 
	 * @Title: getUUIDWithLineUpper 
	 * @Description: 获取带横线的大写UUID
	 * @return  带横线的大写UUID
	 * @return: String
	 */
	public static String getUUIDWithLineUpper() {
		return getUUIDWithLine().toUpperCase();
	}
	
	/**
	 * 
	 * @Title: getUUIDWithOutLineUpper 
	 * @Description: 获取不带横线的大写UUID
	 * @return 不带横线的大写UUID
	 * @return: String
	 */
	public static String getUUIDWithOutLineUpper() {
		return getUUIDWithOutLine().toUpperCase();
	}

}
