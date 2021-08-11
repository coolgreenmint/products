/**   
 * Copyright © 2021 mint. All rights reserved.
 * 
 * @Title: WordFile.java 
 * @Prject: tombs
 * @Package: com.mint.tombs.base.officefile 
 * @Description: 使用java处理word文档
 * @author: mint   
 * @date: 2021年8月10日 下午4:26:28 
 * @version: V1.0   
 */
package com.mint.tombs.base.officefile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * @ClassName: WordFile
 * @Description: 使用java处理word文档
 * @author: mint
 * @date: 2021年8月10日 下午4:26:28
 * 
 *        XWPFDocument 文档对象 XWPFParagraph 文档段落 XWPFRun 一个片段（样式相同的一段） XWPFPicture
 *        图片 XWPFTable 表格
 * 
 */
public class WordFile {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		readDocxByPOI("e://files//test.docx");

	}

	public static void readDocxByPOI(String filePath) throws FileNotFoundException, IOException {

		// 打开一个word文件
		try (FileInputStream fis = new FileInputStream(new File(filePath))) {

			// 循环遍历文档，显示文档内容类型
			XWPFDocument document = new XWPFDocument(fis);

			List<IBodyElement> bodyElements = document.getBodyElements();

			for (IBodyElement ib : bodyElements) {
				BodyElementType type = ib.getElementType();
				// 段落
				if (type == BodyElementType.PARAGRAPH) {
					XWPFParagraph paragraph = (XWPFParagraph) ib;
					int i = 0;
					int j = 0;
					for (XWPFRun run : paragraph.getRuns()) {
						i++;
						System.out.println(run.text());
						// 循环遍历run中存在的图片
						for (XWPFPicture pic : run.getEmbeddedPictures()) {
							j++;
							String randomName = "pic" + i + j + ".jpg";
							pic.getCTPicture().save(new File("e://files//" + randomName));
							System.out.println("图片保存完毕");
						}
					}
				}
				// 表格
				else if (type == BodyElementType.TABLE) {
					// System.out.println("表格-->>" + ib.getPart());
				}
				// 内容控制
				else if (type == BodyElementType.CONTENTCONTROL) {

				}

			}

			document.close();
		}

		System.out.println("文档读取完毕");

	}

}
