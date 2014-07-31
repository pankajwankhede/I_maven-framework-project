package net.aimeizi.tutorials.thumbnailator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.junit.Test;

/**
 * Java缩略图生成类库
 * @see https://code.google.com/p/thumbnailator/wiki/Examples
 * @author 冯靖
 *
 */
/**
 * @author welcome
 *
 */
public class Thumbnailator {


	/**
	 * 指定大小进行缩放 
	 */
	@Test
	public void example1() throws Exception{
		//size(宽度, 高度)
		/*  
		 * 若图片横比200小，高比300小，不变  
		 * 若图片横比200小，高比300大，高缩小到300，图片比例不变  
		 * 若图片横比200大，高比300小，横缩小到200，图片比例不变  
		 * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300  
		 */ 
		Thumbnails.of("src/main/resources/example.jpg").size(200, 300).toFile("src/main/resources/200x300.jpg");
		Thumbnails.of("src/main/resources/example.jpg").size(2560, 2048).toFile("src/main/resources/2560x2048.jpg");

	}
	
	
	/**
	 * 按照比例进行缩放 
	 */
	@Test
	public void example2() throws Exception{
		//scale(比例)
		Thumbnails.of("src/main/resources/example.jpg") 
		        .scale(0.25f)
		        //.outputQuality(0.8)
		        .toFile("src/main/resources/25%.jpg");

		Thumbnails.of("src/main/resources/example.jpg") 
		        .scale(1.10f)
		        //.outputQuality(0.8)
		        .toFile("src/main/resources/110%.jpg");

	}
	
	
	/**
	 * 不按照比例，指定大小进行缩放 
	 */
	@Test
	public void example3() throws Exception{
		//keepAspectRatio(false) 默认是按照比例缩放的
		Thumbnails.of("src/main/resources/example.jpg") 
		        .size(200, 200) 
		        .keepAspectRatio(false) 
		        .toFile("src/main/resources/200x200.jpg");
	}
	
	
	/**
	 * 旋转 
	 */
	@Test
	public void example4() throws Exception{
		//rotate(角度),正数：顺时针 负数：逆时针
		Thumbnails.of("src/main/resources/example.jpg") 
				.size(1280, 1024)
		        .rotate(90) 
		        .toFile("src/main/resources/rotate+90.jpg"); 

		Thumbnails.of("src/main/resources/example.jpg") 
				.size(1280, 1024)
		        .rotate(-90) 
		        .toFile("src/main/resources/rotate-90.jpg"); 

	}
	
	
	/**
	 * 水印
	 */
	@Test
	public void example5() throws Exception{
		//watermark(位置，水印图，透明度)
		
		//左上角
		Thumbnails.of("src/main/resources/example.jpg") 
		.size(1280, 1024)
        .watermark(Positions.TOP_LEFT, ImageIO.read(new File("src/main/resources/watermark.png")), 0.5f) 
        .outputQuality(0.8f) 
        .toFile("src/main/resources/a380_watermark_top_left.jpg");
		
		//左下角
		Thumbnails.of("src/main/resources/example.jpg") 
		.size(1280, 1024)
        .watermark(Positions.BOTTOM_LEFT, ImageIO.read(new File("src/main/resources/watermark.png")), 0.5f) 
        .outputQuality(0.8f) 
        .toFile("src/main/resources/a380_watermark_bottom_left.jpg");
		
		
		//右上角
		Thumbnails.of("src/main/resources/example.jpg") 
		.size(1280, 1024)
		.watermark(Positions.TOP_RIGHT, ImageIO.read(new File("src/main/resources/watermark.png")), 0.5f) 
		.outputQuality(0.8f) 
		.toFile("src/main/resources/a380_watermark_top_right.jpg");
		
		
		//右下角
		Thumbnails.of("src/main/resources/example.jpg") 
				.size(1280, 1024)
		        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("src/main/resources/watermark.png")), 0.5f) 
		        .outputQuality(0.8f) 
		        .toFile("src/main/resources/a380_watermark_bottom_right.jpg");

		//局中
		Thumbnails.of("src/main/resources/example.jpg") 
				.size(1280, 1024)
		        .watermark(Positions.CENTER, ImageIO.read(new File("src/main/resources/watermark.png")), 0.5f) 
		        .outputQuality(0.8f) 
		        .toFile("src/main/resources/a380_watermark_center.jpg");

	}
	
	
	/**
	 * 裁剪 
	 */
	@Test
	public void example6() throws Exception{
		//sourceRegion()
		//图片中心400*400的区域
		Thumbnails.of("src/main/resources/example.jpg")
				.sourceRegion(Positions.CENTER, 400,400)
				.size(200, 200)
		        .keepAspectRatio(false) 
		        .toFile("src/main/resources/a380_region_center.jpg");

		//图片右下400*400的区域
		Thumbnails.of("src/main/resources/example.jpg")
				.sourceRegion(Positions.BOTTOM_RIGHT, 400,400)
				.size(200, 200)
		        .keepAspectRatio(false) 
		        .toFile("src/main/resources/a380_region_bootom_right.jpg");

		//指定坐标
		Thumbnails.of("src/main/resources/example.jpg")
				.sourceRegion(300, 250, 260, 260)
				.size(200, 200)
		        .keepAspectRatio(false) 
		        .toFile("src/main/resources/a380_region_coord.jpg");

	}
	
	
	/**
	 * 转化图像格式 
	 */
	@Test
	public void example7() throws Exception{
		//outputFormat(图像格式)
		Thumbnails.of("src/main/resources/example.jpg") 
				.size(1280, 1024)
		        .outputFormat("png") 
		        .toFile("src/main/resources/a380_1280x1024.png"); 

		Thumbnails.of("src/main/resources/example.jpg") 
				.size(1280, 1024)
		        .outputFormat("gif") 
		        .toFile("src/main/resources/a380_1280x1024.gif"); 
	}
	
	
	
	/**
	 * 输出到OutputStream
	 */
	@Test
	public void example8() throws Exception{
		//toOutputStream(流对象)
		OutputStream os = new FileOutputStream("src/main/resources/a380_1280x1024_OutputStream.png");
		Thumbnails.of("src/main/resources/example.jpg") 
				.size(1280, 1024)
		        .toOutputStream(os);

	}
	
	
	/**
	 * 输出到BufferedImage
	 */
	@Test
	public void example9() throws Exception{
		//asBufferedImage() 返回BufferedImage
		BufferedImage thumbnail = Thumbnails.of("src/main/resources/example.jpg") 
				.size(1280, 1024)
				.asBufferedImage();
		ImageIO.write(thumbnail, "jpg", new File("src/main/resources/a380_1280x1024_BufferedImage.jpg")); 

	}
	
	
	
	
	
	
	
	
}
