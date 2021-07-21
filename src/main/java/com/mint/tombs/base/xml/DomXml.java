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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
		// 构建读取器
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new File("src/main/resources/base/xml/Student.xml"));
		// 开始获取根元素
		NodeList nodes = document.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			// 从根元素开始递归（根元素可能有多个）
			Node firstNode = nodes.item(0);
			getNode(firstNode);
		}

	}

	/**
	 * 
	 * @Title: readXmlWithDomByTagName
	 * @Description: 根据xml的TagName获取节点信息
	 * @return: void
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void readXmlWithDomByTagName() throws ParserConfigurationException, SAXException, IOException {
		// 构建读取器
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = db.parse(new File("src/main/resources/base/xml/Student.xml"));

		NodeList nodes = doc.getElementsByTagName("name");
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getNodeName() + "-->>" + nodes.item(i).getTextContent());
		}

	}

	/**
	 * 
	 * @Title: writeXmlWithDom
	 * @Description: 使用dom方式写入xml文件
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @return: void
	 */
	public static void writeXmlWithDom()
			throws SAXException, IOException, ParserConfigurationException, TransformerException {
		// 构建写入器
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = db.newDocument();

		Element eStu = doc.createElement("student");

		Element eAge = doc.createElement("age");
		eAge.appendChild(doc.createTextNode("19"));

		Element eSex = doc.createElement("sex");
		eSex.appendChild(doc.createTextNode("women"));

		Element eName = doc.createElement("name");
		eName.appendChild(doc.createTextNode("张三丰子"));

		Element eAddress = doc.createElement("address");
		eAddress.appendChild(doc.createTextNode("四川省重庆市"));

		eStu.appendChild(eAge);
		eStu.appendChild(eSex);
		eStu.appendChild(eName);
		eStu.appendChild(eAddress);

		doc.appendChild(eStu);

		//定义dom传输
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		// 定义目标文件
		File file = new File("src/main/resources/base/xml/NewStudent.xml");
		StreamResult result = new StreamResult(file);

		transformer.transform(source, result);
		System.out.println("文件写入完毕！");

	}

	/**
	 * 
	 * @Title: getNode
	 * @Description: 递归获取节点信息
	 * @param node 当前节点
	 * @return: void
	 */
	public static void getNode(Node node) {
		// 如果节点是element元素节点，则输出节点名称
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			System.out.println(node.getNodeName() + "-->>" + node.getTextContent());
		}

		if (node.hasChildNodes()) {
			// System.out.print("当前节点下有：" + node.getChildNodes().getLength() + "个子节点。");
			// 如果不是子节点，则递归取节点
			for (int i = 0; i < node.getChildNodes().getLength(); i++) {
				Node currentNode = node.getChildNodes().item(i);
				getNode(currentNode);
			}
		}
	}

	public static void main(String[] args) {
		try {
			// readXmlWithDom();
			// readXmlWithDomByTagName();
			writeXmlWithDom();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

}
