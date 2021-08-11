/**   
 * Copyright © 2021 mint. All rights reserved.
 * 
 * @Title: BarCode.java 
 * @Prject: tombs
 * @Package: com.mint.tombs.base.image 
 * @Description: 使用java处理条形码
 * @author: mint
 * @date: 2021年8月9日 上午10:41:54 
 * @version: V1.0   
 */
package com.mint.tombs.base.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @ClassName: BarCode
 * @Description: 使用java处理条形码
 * @author: mint
 * @date: 2021年8月9日 上午10:41:54
 */
public class BarCode {

	public static void main(String[] args) throws WriterException, IOException, NotFoundException {

		// System.out.println(new String("锄禾日当午".getBytes()));

		// create1dBarCode("1989072823000", 100, 50, new
		// File("e://images//barcode.jpg"));

		// readBarCode(new File("e://images//barCode.jpg"));

		createQRCode("锄禾日当午，汗滴禾下土，谁知盘中餐，粒粒皆辛苦。", new File("e://images//二维码.jpg"));

		readQRCode(new File("e://images//二维码.jpg"));

	}

	/**
	 * 
	 * @Title: create1dBarCode
	 * @Description: 使用zxing生成条形码
	 * @param content 写入的文本
	 * @param width   产生的宽度
	 * @param height  产生的高度
	 * @param file    输出的文件
	 * @throws WriterException
	 * @throws IOException
	 * @return: void
	 */
	public static void create1dBarCode(String content, int width, int height, File file)
			throws WriterException, IOException {

		MultiFormatWriter writer = new MultiFormatWriter();

		BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.CODE_128, width, height, null);
		FileOutputStream fos = new FileOutputStream(file);

		ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "jpg", fos);
		fos.flush();

		System.out.println("条形码生成完毕");
	}

	/**
	 * 
	 * @Title: createQRCode
	 * @Description: 将内容写入到二维码
	 * @param content 要写入的内容
	 * @param file    要写入的文件
	 * @throws WriterException
	 * @throws IOException
	 * @return: void
	 */
	public static void createQRCode(String content, File file) throws WriterException, IOException {
		// 定义属性集合
		HashMap<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 设置字符编码
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // 定义错误级别
		hints.put(EncodeHintType.MARGIN, 2);

		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);

		Path path = file.toPath();
		MatrixToImageWriter.writeToPath(bitMatrix, "jpg", path);
		System.out.println("二维码生成完毕");
	}

	/**
	 * 读取条形码
	 * 
	 * @Title: readBarCode
	 * @Description: 读取条形码
	 * @param file
	 * @return: void
	 * @throws IOException
	 * @throws NotFoundException
	 */
	public static void readBarCode(File file) throws IOException, NotFoundException {

		BufferedImage img = ImageIO.read(file);

		LuminanceSource source = new BufferedImageLuminanceSource(img);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

		HashMap<DecodeHintType, Object> hints = new HashMap();

		hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
		hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
		hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

		Result result = new MultiFormatReader().decode(bitmap, hints);
		System.out.println("解析结果：");
		System.out.println(new String(result.getText().getBytes()));
	}

	public static void readQRCode(File file) throws IOException, NotFoundException {

		BufferedImage img = ImageIO.read(file);

		LuminanceSource source = new BufferedImageLuminanceSource(img);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

		HashMap<DecodeHintType, Object> hints = new HashMap();

		hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
		// hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
		// hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

		Result result = new MultiFormatReader().decode(bitmap, hints);
		System.out.println("解析结果：");
		System.out.println(result.getText());
		System.out.println("二维码格式：" + result.getBarcodeFormat());
	}

}
