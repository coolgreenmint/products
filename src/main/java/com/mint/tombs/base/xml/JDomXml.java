/**   
 * Copyright © 2021 mint. All rights reserved.
 * 
 * @Title: JDomXml.java 
 * @Prject: tombs
 * @Package: com.mint.tombs.base.xml 
 * @Description: 使用第三方库jdom操作xml文件
 * @author: mint   
 * @date: 2021年7月22日 下午1:39:00 
 * @version: V1.0   
 */
package com.mint.tombs.base.xml;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * @ClassName: JDomXml
 * @Description: 使用jdom操作xml
 * @author: mint
 * @date: 2021年7月22日 下午1:39:00
 */
public class JDomXml {

	public static void main(String[] args) throws JDOMException, IOException {

		readXMLByJDomWithSax();

	}

	/**
	 * 
	 * @Title: readXMLByJDomWithSax
	 * @Description: 使用jdom的sax方式读取xml文件
	 * @throws JDOMException
	 * @throws IOException
	 * @return: void
	 */
	public static void readXMLByJDomWithSax() throws JDOMException, IOException {

		// 新建SAX构建器
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(new File("src/main/resources/base/xml/Student.xml"));

		// 获取根节点
		Element root = doc.getRootElement();
		System.out.println("开始解析根节点：" + root.getName());
		System.out.println("----------------------------");

		// 遍历获取最底层元素
		// getNodeInfo(root);

		List<Element> lstStudent = root.getChildren("student");
		for (Element element : lstStudent) {
			System.out.println(element.getChildText("name"));
			System.out.println(element.getChildText("age"));
			System.out.println(element.getChildText("sex"));
			System.out.println(element.getChildText("address"));
			System.out.println("----------------------");
		}
	}

	/**
	 * 
	 * @Title: getNodeInfo
	 * @Description: 遍历获取最底层节点信息
	 * @param element
	 * @return: void
	 */
	public static void getNodeInfo(Element element) {
		if (element.getChildren() == null || element.getChildren().size() == 0) {
			System.out.println(element.getName() + "-->>" + element.getText());
		} else {
			for (Object e : element.getChildren()) {
				Element currentE = (Element) e;
				getNodeInfo(currentE);
			}
		}
	}

}
