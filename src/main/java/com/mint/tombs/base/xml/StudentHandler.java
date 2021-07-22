/**   
 * Copyright © 2021 mint. All rights reserved.
 * 
 * @Title: StudentHandler.java 
 * @Prject: tombs
 * @Package: com.mint.tombs.base.xml 
 * @Description: 使用SAX方式处理xml文档的默认处理类
 * @author: mint   
 * @date: 2021年7月22日 上午10:04:30 
 * @version: V1.0   
 */
package com.mint.tombs.base.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @ClassName: StudentHandler
 * @Description: student.xml方式默认处理类
 * @author: mint
 * @date: 2021年7月22日 上午10:04:30
 */
public class StudentHandler extends DefaultHandler {

	// 定义存储解析内容列表
	private List<String> lstNames = null;

	/**
	 * 定义标识变量，如果是name标签，则为 true
	 */
	private boolean isName = false;

	/*
	 * (non Javadoc)
	 * 
	 * @Title: startDocument
	 * 
	 * @Description: TODO
	 * 
	 * @throws SAXException
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		lstNames = new ArrayList<String>();
		System.out.println("开始解析模板");
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: startElement
	 * 
	 * @Description: TODO
	 * 
	 * @param uri
	 * 
	 * @param localName
	 * 
	 * @param qName
	 * 
	 * @param attributes
	 * 
	 * @throws SAXException
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("开始解析" + qName);
		if (qName.equals("name")) {
			isName = true;
		}
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: characters
	 * 
	 * @Description: TODO
	 * 
	 * @param ch
	 * 
	 * @param start
	 * 
	 * @param length
	 * 
	 * @throws SAXException
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		if (isName) {
			lstNames.add(new String(ch, start, length));
		}
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: endElement
	 * 
	 * @Description: TODO
	 * 
	 * @param uri
	 * 
	 * @param localName
	 * 
	 * @param qName
	 * 
	 * @throws SAXException
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		isName = false;
		System.out.println("结束解析" + qName);
	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: endDocument
	 * 
	 * @Description: TODO
	 * 
	 * @throws SAXException
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("解析结束");
	}

	/**
	 * 
	 * @Title: getAllNames
	 * @Description: 解析xml文件 获取所有名字
	 * @return xml文件中所有的名字
	 * @return: List<String>
	 */
	public List<String> getAllNames() {
		return this.lstNames;
	}

}
