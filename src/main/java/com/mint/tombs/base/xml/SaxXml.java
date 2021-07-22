/**   
 * Copyright © 2021 公司名. All rights reserved.
 * 
 * @Title: SaxXml.java 
 * @Prject: tombs
 * @Package: com.mint.tombs.base.xml 
 * @Description: 使用SAX(Simple API for XML)方式读取xml文件 优点：使用流方式（推模型）,速度快  缺点：无法访问之前的元素
 * @author: mint   
 * @date: 2021年7月22日 上午9:24:00 
 * @version: V1.0   
 */
package com.mint.tombs.base.xml;

import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @ClassName: SaxXml
 * @Description: 使用SAX方式读取xml文件（推模式：每读取到元素就触发事件，速度比dom方式快，但是比较繁琐，所以后期出现了STAX模式）
 *               此模式的核心为
 * @author: mint
 * @date: 2021年7月22日 上午9:24:00
 */
public class SaxXml {

	public static void main(String[] args) throws SAXException, IOException {

		StudentHandler hander = new StudentHandler();
		// 创建读取工厂
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(hander);
		reader.parse("src/main/resources/base/xml/Student.xml");
		System.out.println(hander.getAllNames());
	}

}
