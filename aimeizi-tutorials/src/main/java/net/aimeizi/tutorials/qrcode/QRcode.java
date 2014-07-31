package net.aimeizi.tutorials.qrcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


/**
 * 使用qrgen生成二维码
 * 
 * @author 冯靖
 * 
 */
public class QRcode {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		File file = new File("target","qrcode.png");
		
		String content = "http://aimeizi.net";
		
		String charset = "utf-8";
		
		int width = 320;
		
		int height = 320;
		
		generateCode(content,charset,width,height,file);
		
		readCode(file);
		
	}
	
	
	
	/**
	 * 读取二维码
	 * @param file 二维码源
	 * @throws Exception
	 */
	private static void readCode(File file) throws Exception{
		BufferedImage encodedBufferedImage = ImageIO.read(file) ;
		LuminanceSource source = new BufferedImageLuminanceSource(encodedBufferedImage);
		Result result = new QRCodeReader().decode(new BinaryBitmap(new HybridBinarizer(source)));
		System.out.println(result.getText());
	}




	/**
	 * 生成二维码
	 * @param content 内容
	 * @param charset 字符编码
	 * @param width 宽
	 * @param height 高
	 * @param target 生成后写入路径
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void generateCode(String content,String charset,int width,int height,File target) throws FileNotFoundException,IOException {
		
		ByteArrayOutputStream out = QRCode.from(content).withCharset(charset).withSize(width, height).to(ImageType.PNG).stream();

		OutputStream outStream = new FileOutputStream(target);

		outStream.write(out.toByteArray());

		outStream.flush();
		outStream.close();
		
	}

}
