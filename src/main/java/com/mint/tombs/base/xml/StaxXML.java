/**   
 * Copyright © 2021 mint. All rights reserved.
 * 
 * @Title: StaxXML.java 
 * @Prject: tombs
 * @Package: com.mint.tombs.base.xml 
 * @Description: 使用Stax(Streaming API for XML)处理xml,使用推模型（不像Sax,只要处理标签就会触发事件）
 *               分为基于指针API和基于迭代器API
 * @author: mint   
 * @date: 2021年7月22日 上午10:35:01 
 * @version: V1.0   
 */
package com.mint.tombs.base.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import org.xml.sax.SAXException;

/**
 * @ClassName: StaxXML
 * @Description: 使用Stax(Streaming API for XML)处理xml
 * @author: mint
 * @date: 2021年7月22日 上午10:35:01
 */
public class StaxXML {

	public static void main(String[] args) throws SAXException, FileNotFoundException, XMLStreamException {
		// 基于流模式API读取xml文件
		// readXMLByStream();
		System.out.println("----------------------------");
		readXMLByEvent();
	}

	/**
	 * 
	 * @Title: readXMLByStream
	 * @Description: 使用Stax的流模式读取xml文件
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 * @return: void
	 */
	public static void readXMLByStream() throws FileNotFoundException, XMLStreamException {
		// 构建流模式读取器
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory
				.createXMLStreamReader(new FileInputStream(new File("src/main/resources/base/xml/Student.xml")));

		// 如果有读取事件，则根据事件类型判断
		while (reader.hasNext()) {
			int type = reader.next();
			// 判断开始标签,是否为开始标签事件
			if (type == XMLStreamConstants.START_ELEMENT) {
				// 拿到name属性
				if (reader.getLocalName().equals("name")) {
					System.out.println(reader.getLocalName() + "-->>" + reader.getElementText());
				}
			}

		}

		reader.close();
		System.out.println("读取完毕，关闭文件流");
	}

	/**
	 * 
	 * @Title: readXMLByEvent
	 * @Description: 基于事件迭代器的xml文档读取
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 * @return: void
	 */
	public static void readXMLByEvent() throws FileNotFoundException, XMLStreamException {

		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader reader = factory
				.createXMLEventReader(new FileInputStream(new File("src/main/resources/base/xml/Student.xml")));

		boolean isName = false;
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();

			// 如果是开始标签,并且是名称节点
			if (event.isStartElement()) {
				if (event.asStartElement().getName().getLocalPart().equals("name")) {
					isName = true;
				} else {
					isName = false;
				}
			}
			// 如果是正文内容并且正文内容不为空，且为name标签，则打印显示
			if (event.isCharacters() && event.asCharacters().toString().trim().length() > 0 && isName) {
				System.out.println("name" + "-->>" + event.asCharacters().getData());
			}

		}
		reader.close();
	}

}
