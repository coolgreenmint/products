/**   
 * Copyright © 2021 mint. All rights reserved.
 * 
 * @Title: ImageTest.java 
 * @Prject: tombs
 * @Package: com.mint.tombs.base.image 
 * @Description: java 操作image图像
 * @author: mint   
 * @date: 2021年7月26日 下午11:18:31 
 * @version: V1.0   
 */
package com.mint.tombs.base.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @ClassName: ImageTest
 * @Description: 使用Java语言操作图像
 * @author: mint
 * @date: 2021年7月26日 下午11:18:31
 */
public class ImageTest {

	public static void main(String[] args) throws IOException {
		readAndWriteImg();
	}

	/**
	 * 
	 * @Title: readAndWriteImg
	 * @Description: 读取并写入image图像
	 * @return: void
	 * @throws IOException
	 */
	public static void readAndWriteImg() throws IOException {
		// 将图像读取到内存中
		BufferedImage img = ImageIO.read(new File("e://images//fengjing.jpg"));
		System.out.println("width:" + img.getWidth());
		System.out.println("height:" + img.getHeight());
		ImageIO.write(img, "png", new File("e://images//fengjing.png"));
		System.out.println("文件写入完毕");
	}

}
