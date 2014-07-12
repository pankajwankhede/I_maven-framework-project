package org.mvn.spring.data.jpa.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mvn.spring.data.jpa.model.ColumnInfo;
import org.mvn.spring.data.jpa.model.ContentInfo;
import org.mvn.spring.data.jpa.service.ColumnInfoService;
import org.mvn.spring.data.jpa.service.ContentInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration(value={"classpath:beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ContentInfoServiceTest{
	
	@Resource(name="contentInfoServiceImpl")
	private ContentInfoService  contentInfoService;
	
	@Resource(name="columnInfoServiceImpl")
	private ColumnInfoService  columnInfoService;
	
	@Test
	public void saveContentInfo(){
		
		ContentInfo contentInfo=new ContentInfo();
		
		contentInfo.setConname("苹果明日正式发布第六代iphone");
		contentInfo.setConbody("苹果明日正式发布第六代iphone");
		
		ColumnInfo columnInfo=new ColumnInfo();
		columnInfo.setColname("科技");
		contentInfo.setColumnInfo(columnInfo);
		
		contentInfoService.save(contentInfo);
		
	}
	
	
	
	@Test
	public void saveContentInfoAndColumnInfo(){
		
		ContentInfo contentInfo=new ContentInfo();
		
		contentInfo.setConname("章子怡默认与撒贝宁恋情:自己心里有数就行");
		contentInfo.setConbody("唯一入围多伦多国际电影节主展映单元的华语影片《危险关系》进行北美首映。主演章子怡、张东健等齐聚首映红毯。章子怡接受媒体采访时谈到了感情观，她认为“真正的爱情是存在的”，自称是一个爱情至上的人。问及和撒贝宁之间的绯闻，她表示“自己心里有数就是了”");
		
		ColumnInfo columnInfo=columnInfoService.findByTheColumninfoId(4);
		contentInfo.setColumnInfo(columnInfo);
		
		contentInfoService.save(contentInfo);
		
	}
	
	
	@Test
	public void findByContentId(){
		ContentInfo contentInfo = contentInfoService.findByContentId(1);
		System.out.println(contentInfo.getConname());
		System.out.println(contentInfo.getConbody());
		System.out.println(contentInfo.getColumnInfo().getColname());
	}
	
	
	@Test
	public void findByConnameLike(){
		List<ContentInfo> contentInfos = contentInfoService.findByConnameLike("%钓鱼岛%");
		for (ContentInfo contentInfo : contentInfos) {
			System.out.println(contentInfo.getConname());
			System.out.println(contentInfo.getConbody());
			System.out.println(contentInfo.getColumnInfo().getColname());
		}
	}
	
	@Test
	public void queryById(){
		ContentInfo contentInfo = contentInfoService.queryById(1);
		System.out.println(contentInfo.getConname());
		System.out.println(contentInfo.getConbody());
		System.out.println(contentInfo.getColumnInfo().getColname());
	}
	
	
	@Test
	public void findByColumnInfoColumninfoId(){
		List<ContentInfo> contentInfos = contentInfoService.findByColumnInfoColumninfoId(1);
		for (ContentInfo contentInfo : contentInfos) {
			System.out.println(contentInfo.getConname());
			System.out.println(contentInfo.getConbody());
			System.out.println(contentInfo.getColumnInfo().getColname());
		}
	}
	
	
	@Test
	public void findByColId(){
		List<ContentInfo> contentInfos = contentInfoService.findByColId(4);
		for (ContentInfo contentInfo : contentInfos) {
			System.out.println(contentInfo.getConname());
			System.out.println(contentInfo.getConbody());
			System.out.println(contentInfo.getColumnInfo().getColname());
		}
	}
	
	
	@Test
	public void findByTheColumnInfoColumninfoId(){
		List<ContentInfo> contentInfos = contentInfoService.findByTheColumnInfoColumninfoId(4);
		for (ContentInfo contentInfo : contentInfos) {
			System.out.println(contentInfo.getConname());
			System.out.println(contentInfo.getConbody());
			System.out.println(contentInfo.getColumnInfo().getColname());
			System.out.println("-------------------------------------");
		}
	}
	
	
	
	@Test
	public void modifyContentInfo(){
		
		ContentInfo contentInfo = contentInfoService.queryById(1);
		
		contentInfo.setConname("扎克伯格称豪赌HTML5为公司最大失误");
		contentInfo.setConbody("28岁的Facebook创始人兼CEO马克·扎克伯格今天首次在公司上市后发表了公开讲话。他承认公司股价连续下挫是“令人失望的”，但也同时指出投资者并没未完全明白移动平台增长的潜力。扎克伯格称，眼下并非是Facebook第一次经历起伏，建立任务和打造业务是携手共进的。扎克伯格对公司的未来感到乐观，并认为移动平台一旦做成功了，将会比桌面平台带来更大的收入。");
		
		int n = contentInfoService.modify(contentInfo.getConname(), contentInfo.getConbody(), contentInfo.getColumnInfo().getColumninfoId(), contentInfo.getContentId());
		System.out.println(n);
	}
	
	
	
	
	@Test
	public void findAll(){
		Page<ContentInfo> page = contentInfoService.findAll(new PageRequest(0,10));
		List<ContentInfo> contentInfos = page.getContent();
		
		System.out.println("getTotalElements:"+page.getTotalElements());
		System.out.println("TotalPages:"+page.getTotalPages());
		System.out.println("Number:"+page.getNumber());
		
		for (ContentInfo contentInfo : contentInfos) {
			System.out.println(contentInfo.getConname());
			System.out.println(contentInfo.getConbody());
			System.out.println(contentInfo.getColumnInfo().getColname());
			System.out.println("-------------------------------------");
		}
	}
}
