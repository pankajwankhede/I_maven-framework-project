package org.mvn.spring.data.jpa.service.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mvn.spring.data.jpa.model.ColumnInfo;
import org.mvn.spring.data.jpa.model.ContentInfo;
import org.mvn.spring.data.jpa.service.ColumnInfoService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration(value={"classpath:beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ColumnInfoServiceTest{
	
	@Resource(name="columnInfoServiceImpl")
	private ColumnInfoService  columnInfoService;
	
	@Test
	public void saveColumnInfo(){
		
		ColumnInfo columnInfo=new ColumnInfo();
		columnInfo.setColname("国际新闻");
		columnInfoService.save(columnInfo);
		
	}
	
	
	
	
	@Test
	public void saveColumnInfoAndContentInfo(){
		
		ColumnInfo columnInfo=new ColumnInfo();
		columnInfo.setColname("国内新闻");
		
		Set<ContentInfo> sets=new HashSet<ContentInfo>();
		
		ContentInfo contentInfo=new ContentInfo();
		contentInfo.setConname("日本购买钓鱼岛");
		contentInfo.setConbody("关于日方购买钓鱼岛一事,中国的政策是强烈抗议,在中央气象台天气预报节目中,新增钓鱼岛天气预报.并声明要确保钓鱼岛及周边附属岛屿天气精确无误！可悲的党和国家领导人啊!");
		contentInfo.setColumnInfo(columnInfo);
		sets.add(contentInfo);
		
		contentInfo=new ContentInfo();
		contentInfo.setConname("三星在西安投资30亿建电子工厂");
		contentInfo.setConbody("2012年9月12日三星在西安投资30亿建电子工厂!开工仪式正式启动!");
		contentInfo.setColumnInfo(columnInfo);
		sets.add(contentInfo);
		
		columnInfo.setSets(sets);
		columnInfoService.save(columnInfo);
		
	}
	
	@Test
	public void findByTheColumninfoId(){
		ColumnInfo columnInfo = columnInfoService.findByTheColumninfoId(2);
		System.out.println("栏目id:"+columnInfo.getColumninfoId()+"\t栏目名称:"+columnInfo.getColname());
		System.out.println("该栏目下包含的内容数:"+columnInfo.getSets().size());
		System.out.println("------------------------------");
		for (Iterator<ContentInfo> iterator = columnInfo.getSets().iterator(); iterator.hasNext();) {
			ContentInfo info = iterator.next();
			System.out.println("内容id:"+info.getContentId());
			System.out.println("内容Conname:"+info.getConname());
			System.out.println("内容Conbody:"+info.getConbody());
			System.out.println("内容所在栏目名称:"+info.getColumnInfo().getColname());
			System.out.println("------------------------------");
		}
	}
	
	
	@Test
	public void findByConnameLike(){
		List<ColumnInfo> columnInfos = columnInfoService.findByColnameLike("%新闻%");
		for (ColumnInfo columnInfo : columnInfos) {
			System.out.println(columnInfo.getColumninfoId()+"\t"+columnInfo.getColname());
			Set<ContentInfo> sets=columnInfo.getSets();
			for (Iterator<ContentInfo> iterator = sets.iterator(); iterator.hasNext();) {
				ContentInfo info = (ContentInfo) iterator.next();
				System.out.println("内容id:"+info.getContentId());
				System.out.println("内容Conname:"+info.getConname());
				System.out.println("内容Conbody:"+info.getConbody());
				System.out.println("内容所在栏目名称:"+info.getColumnInfo().getColname());
				System.out.println("--------------------------------------");
			}
		}
	}
	
	
}
