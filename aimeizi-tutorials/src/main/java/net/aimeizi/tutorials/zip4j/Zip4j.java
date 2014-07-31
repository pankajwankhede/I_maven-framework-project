package net.aimeizi.tutorials.zip4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.junit.Before;
import org.junit.Test;

/**
 * 使用zip4j压缩或解压文件
 * @author 冯靖
 *
 */
public class Zip4j {
	
	
	@Before
	public void setup() throws Exception{
		ZipFile zipFile = new ZipFile("src/main/resources/AddFilesDeflateComp.zip");
		if(zipFile.getFile().exists()){
			zipFile.getFile().delete();
		}
	}
	
	
	/**
	 * 示例1 创建压缩包添加文件到压缩包中（未设置加密）
	 */
	@Test
	public void example1(){
		try {
            
            ZipFile zipFile = new ZipFile("src/main/resources/AddFilesDeflateComp.zip");
             
            ArrayList<File> filesToAdd = new ArrayList<File>();
            filesToAdd.add(new File("src/main/resources/sample.txt"));
            filesToAdd.add(new File("src/main/resources/zip4j.txt"));
            filesToAdd.add(new File("src/main/resources/zip4j-1.3.2.jar"));
             
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
            zipFile.addFiles(filesToAdd, parameters);
            
            CompressUtil.printZipInfo("src/main/resources/AddFilesDeflateComp.zip");
            
            
        } catch (ZipException e) {
            e.printStackTrace();
        }
	}
	
	
	/**
	 * 示例2 创建压缩包添加文件到指定目录中进行压缩
	 */
	@Test
	public void example2(){
		try {
            ZipFile zipFile = new ZipFile("src/main/resources/AddFilesDeflateComp.zip");
            ArrayList<File> filesToAdd = new ArrayList<File>();
            filesToAdd.add(new File("src/main/resources/sample.txt"));
            filesToAdd.add(new File("src/main/resources/zip4j.txt"));
            filesToAdd.add(new File("src/main/resources/zip4j-1.3.2.jar"));
             
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
             
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            parameters.setRootFolderInZip("target/");
 
            zipFile.addFiles(filesToAdd, parameters);
        } catch (ZipException e) {
            e.printStackTrace();
        } 
	}
	
	/**
	 * 示例3 添加文件夹到压缩包中
	 */
	@Test
	public void example3(){
		try {
            
            ZipFile zipFile = new ZipFile("src/main/resources/AddFolder.zip");
            String folderToAdd = "src/main/resources";
         
            ZipParameters parameters = new ZipParameters();
             
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            zipFile.addFolder(folderToAdd, parameters);
             
        } catch (ZipException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 示例4 创建加密压缩包
	 */
	@Test
	public void example4(){
		try {
            ZipFile zipFile = new ZipFile("src/main/resources/AddFilesWithAESZipEncryption.zip");
 
            ArrayList<File> filesToAdd = new ArrayList<File>();
            filesToAdd.add(new File("src/main/resources/sample.txt"));
            filesToAdd.add(new File("src/main/resources/zip4j.txt"));
            filesToAdd.add(new File("src/main/resources/zip4j-1.3.2.jar"));
            
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
             
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
            parameters.setEncryptFiles(true);
             
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
             
 
            parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
            parameters.setPassword("123456");
     
            zipFile.addFiles(filesToAdd, parameters);
        } catch (ZipException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 示例5 创建分卷压缩包
	 */
	@Test
	public void example5(){
		try {
	         
            ZipFile zipFile = new ZipFile("src/main/resources/CreateSplitZipFile.zip");
 
            ArrayList<File> filesToAdd = new ArrayList<File>();
            filesToAdd.add(new File("src/main/resources/sample.txt"));
            filesToAdd.add(new File("src/main/resources/zip4j.txt"));
            filesToAdd.add(new File("src/main/resources/zip4j-1.3.2.jar"));
             
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
             
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
             
            zipFile.createZipFile(filesToAdd, parameters, true, 65536);
        } catch (ZipException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 示例6 通过流的方式添加文件到压缩包中
	 */
	@Test
	public void example6(){
		InputStream is = null;
        
        try {
            ZipFile zipFile = new ZipFile("src/main/resources/AddStreamToZip.zip");
 
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setFileNameInZip("sample.txt");
            parameters.setSourceExternalStream(true);
             
            is = new FileInputStream("src/main/resources/sample.txt");
            zipFile.addStream(is, parameters);
             
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	
	/**
	 * 示例7 解压压缩文件
	 */
	@Test
	public void example7(){
		
		example4();
		
		try {
            ZipFile zipFile = new ZipFile("src/main/resources/AddFilesWithAESZipEncryption.zip");
            if (zipFile.isEncrypted()) {
                // if yes, then set the password for the zip file
                zipFile.setPassword("123456");
                zipFile.extractAll("src/main/resources/zipfile");
            }
        } catch (ZipException e) {
            e.printStackTrace();
        }
	}
	
}
