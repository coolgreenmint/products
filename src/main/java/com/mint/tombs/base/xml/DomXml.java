/**   
 * Copyright © 2021 mint. All rights reserved.
 * 
 * @Title: DomXml.java 
 * @Prject: tombs
 * @Package: com.mint.tombs.base.xml 
 * @Description: 使用dom方式处理xml文件的类
 * @author: mint   
 * @date: 2021年7月21日 上午10:32:42 
 * @version: V1.0   
 */
package com.mint.tombs.base.xml;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @ClassName: DomXml
 * @Description: 使用Dom方式处理xml文档，可读写。工作方式：通过将整个xml文档放入内存，按照结构读写的方式操作xml。
 *               缺点：如果xml文档太大，则会引起程序崩溃。
 * @author: mint
 * @date: 2021年7月21日 上午10:32:42
 */
public class DomXml {

	/**
	 * 
	 * @Title: readXmlWithDom
	 * @Description: 通过dom方式读取xml文件
	 * @return: void
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void readXmlWithDom() throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new File("src/main/resources/base/xml/Student.xml"));
		// 开始获取根元素
		NodeList nodes = document.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(0).getNodeName() + "-->>" + nodes.item(0).getNodeValue());
		}

	}

	public static Node getNodeInfo(Node node) {
		if (node.hasChildNodes()) {
			return getNodeInfo(node.getFirstChild());
		} else {
			System.out.println(node.getNodeName() + "-->>" + node.getNodeValue());
			return null;
		}
	}

	public static void main(String[] args) {

		try {
			readXmlWithDom();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
