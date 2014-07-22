package net.aimeizi.spring.data.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import net.aimeizi.spring.data.example.config.WebConfig;
import net.aimeizi.spring.data.example.entities.Dept;
import net.aimeizi.spring.data.example.entities.Emp;
import net.aimeizi.spring.data.example.service.EmpService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=WebConfig.class)
public class EmpRepositoryTest {

	
	@Autowired
	EmpService empService;
	
	
	private static IndexColorModel createIndexColorModel() { 
		
        byte[] r = new byte[ 256 ]; 
        byte[] g = new byte[ 256 ]; 
        byte[] b = new byte[ 256 ]; 

        // now fill in the r,g, and b arrays with a ramp or 0 
        for ( int i = 0; i < 256 ; i++ ){ 
          r[i] = (byte) i; 
          g[i] = (byte) i; 
          b[i] = (byte) i; 
        } 
        return new IndexColorModel( 8, 256, r, g, b );
        
    } 
	
	
	@Test
	public void save(){
		
		Emp emp = new Emp();
		emp.setEmpname("马云");
		emp.setEmpaddress("浙江杭州");
		
		Dept dept = new Dept();
		
		dept.setDeptname("研发部");
	
		dept.setDeptphonenumber("110");
		
		emp.setDept(dept);
		
		empService.save(emp );
		
	}
	
	@Test
	public void delete(){
		Emp emp = empService.findOne(2);
		empService.delete(emp);
	}
	
	@Test
	public void update(){
		
		Emp emp = empService.findOne(5);
		
		emp.setEmpname("马化腾");
		emp.setEmpaddress("广东深圳");
		
		Dept dept = emp.getDept();
		dept.setDeptphonenumber("120");
		dept.setDeptname("大客户服务部");
		
		emp.setDept(dept);
		
		empService.update(emp);
		
	}
	
	@Test
	public void imageEmail() throws Exception{
		
		String email = "sxyx2008@163.com";

		int height = 22;
		BufferedImage bi = new BufferedImage(255, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		Font mFont = new Font("Verdana", Font.PLAIN, 14);
		g.setFont(mFont);
		g.drawString(email, 2, 19);
		FontMetrics fm = g.getFontMetrics();
		int new_width = fm.charsWidth(email.toCharArray(), 0, email.length()) + 4;
		int new_height = fm.getHeight();
		BufferedImage nbi = new BufferedImage(new_width, new_height,
				BufferedImage.TYPE_BYTE_INDEXED, createIndexColorModel());
		Graphics2D g2 = (Graphics2D) nbi.getGraphics();
		g2.setColor(new Color(0, 0, 0, 0));// 透明
		g2.fillRect(0, 0, new_width, new_height);
		g2.setFont(mFont);
		g2.setColor(new Color(200, 0, 0));
		g2.drawString(email, 2, new_height - 4);

		ImageIO.write(nbi, "gif", new FileOutputStream(
				"target/sxyx2008@163.com.gif"));
	}
	
}
