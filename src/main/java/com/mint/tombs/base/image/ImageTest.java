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

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * @ClassName: ImageTest
 * @Description: 使用Java语言操作图像
 * @author: mint
 * @date: 2021年7月26日 下午11:18:31
 */
public class ImageTest {

	public static void main(String[] args) throws IOException {
		// readAndWriteImg();
		// readSpeedTest();
		// cropImage("e://images//1.jpeg", "e://images//2.jpeg", 30, 60, 400, 600,
		// "jpg");
		combineImg("e://images//1.jpeg", "e://images//maque.jpg", "jpg", "e://images//combin.jpeg");
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
		BufferedImage img = ImageIO.read(new File("e://images//mq.jfif"));
		System.out.println("width:" + img.getWidth());
		System.out.println("height:" + img.getHeight());
		ImageIO.write(img, "png", new File("e://images//maque.jpg"));
		System.out.println("文件写入完毕");
	}

	/**
	 * 图片加载速度测试
	 * 
	 * @Title: readSpeedTest
	 * @Description: 图片加载速度测试
	 * @return: void
	 * @throws IOException
	 */
	public static void readSpeedTest() throws IOException {

		// 普通方式读取图片测试（imageio会内置多种图片读取器，根据图片自动选择读取器）
		long startTime = System.nanoTime();
		BufferedImage image = ImageIO.read(new File("E:\\images\\1.jpeg"));
		System.out.println("图片高度：" + image.getHeight());
		System.out.println("图片宽度：" + image.getWidth());
		long endTime = System.nanoTime();
		System.out.println("读取图片共耗时：" + (endTime - startTime) / (1000 * 1000) + "毫秒");

		// 指定读取器方式读取图片,速度快（前提是首先知道了图片类型）
		long start = System.nanoTime();
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = readers.next();
		System.out.println("读取器名称：" + reader.getFormatName());
		ImageInputStream iis = ImageIO.createImageInputStream(new File("E:\\images\\1.jpg"));
		reader.setInput(iis, false);
		System.out.println("图片高度：" + reader.getHeight(0));
		System.out.println("图片宽度：" + reader.getWidth(0));

		long end = System.nanoTime();
		System.out.println("读取图片共耗时：" + (end - start) / (1000 * 1000) + "毫秒");
	}

	/**
	 * 从图片中截取一部分并保存为特定格式的图片
	 * 
	 * @Title: cropImage
	 * @Description: 从图片中截取一部分并保存为特定格式的图片
	 * @param fromPath         源图片位置
	 * @param toPath           目的图片位置
	 * @param x                x轴
	 * @param y                y轴
	 * @param width            截取宽度
	 * @param height           截取高度
	 * @param writeImageformat 写入图片格式
	 * @return: void
	 * @throws IOException
	 */
	public static void cropImage(String fromPath, String toPath, int x, int y, int width, int height,
			String writeImageformat) throws IOException {

		FileInputStream fis = new FileInputStream(new File(fromPath));
		ImageInputStream iis = ImageIO.createImageInputStream(fis);

		// 取得读取器
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = readers.next();
		reader.setInput(iis);

		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, width, height);
		param.setSourceRegion(rect);

		// 读取截取的内容
		BufferedImage bi = reader.read(0, param);
		// 将读取图片写入
		ImageIO.write(bi, writeImageformat, new File(toPath));
		System.out.println("图片截取完毕");
	}

	/**
	 * 拼接两张图片
	 * 
	 * @Title: combineImg
	 * @Description: 将两张图片拼成一张图片
	 * @param filePath1 文件1路径
	 * @param filePath2 文件2路径
	 * @throws IOException
	 * @return: void
	 */
	public static void combineImg(String filePath1, String filePath2, String imgFormat, String toFile)
			throws IOException {
		// 读取图片1的rgb内容
		BufferedImage img1 = ImageIO.read(new File(filePath1));
		int width1 = img1.getWidth();
		int height1 = img1.getHeight();
		int[] rgb1 = new int[width1 * height1];
		rgb1 = img1.getRGB(0, 0, width1, height1, rgb1, 0, width1);

		// 读取图片2的rgb内容
		BufferedImage img2 = ImageIO.read(new File(filePath2));
		int width2 = img2.getWidth();
		int height2 = img2.getHeight();
		int[] rgb2 = new int[width2 * height2];
		rgb2 = img2.getRGB(0, 0, width2, height2, rgb2, 0, width2);

		// 开始合并图像
		int height3 = height1 > height2 ? height1 : height2;
		int width3 = width1 + width2;
		BufferedImage imgNew = new BufferedImage(width3, height3, BufferedImage.TYPE_INT_RGB);

		imgNew.setRGB(0, 0, width1, height1, rgb1, 0, width1);
		imgNew.setRGB(width1, 0, width2, height2, rgb2, 0, width2);

		// 写入文件
		ImageIO.write(imgNew, imgFormat, new File(toFile));
		System.out.println("图片合成完毕");

	}

}
