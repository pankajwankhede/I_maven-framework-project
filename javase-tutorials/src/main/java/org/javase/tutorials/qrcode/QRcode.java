package org.javase.tutorials.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


/**
 * 使用qrgen生成二维码
 * 
 * @author welcome
 * 
 */
public class QRcode {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		ByteArrayOutputStream out = QRCode.from("雪山飞鹄").withCharset("utf-8").withSize(480, 320).to(ImageType.PNG).stream();

		OutputStream outStream = new FileOutputStream(new File("target","qrcode.png"));

		outStream.write(out.toByteArray());

		outStream.flush();
		outStream.close();
		
	}

}
