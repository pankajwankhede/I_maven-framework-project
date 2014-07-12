package org.mvn.spring.data.jpa.service;

import java.util.List;

import org.mvn.spring.data.jpa.model.ContentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface ContentInfoService {
	
	/**
	 * 根据内容id查询内容
	 * @param contentId
	 * @return
	 */
	ContentInfo findByContentId(int contentId);
	
	/**
	 * 根据内容conname模糊查询
	 * @param conname
	 * @return
	 */
	List<ContentInfo> findByConnameLike(String conname);
	
	/**
	 * 根据内容conname模糊查询支持分页查询
	 * @param conname
	 * @param pageable
	 * @return
	 */
	List<ContentInfo> findByConnameLike(String conname,Pageable pageable);
	
	/**
	 * 基于注解@Query的查询
	 * @param contentId
	 * @return
	 */
	@Query(value="select a from org.mvn.spring.data.jpa.model.ContentInfo a where a.contentId=?1 ")
	ContentInfo queryById(int contentId);
	
	/**
	 * 属性嵌套查询
	 * @param columninfoId  ContentInfo->columnInfo->columninfoId
	 * @return
	 */
	List<ContentInfo> findByColumnInfoColumninfoId(int columninfoId);
	
	@Query(value="select a from org.mvn.spring.data.jpa.model.ContentInfo a where a.columnInfo.columninfoId=?1")
	List<ContentInfo> findByColId(int columninfoId);
	
	/**
	 * 根据@NamedQuery(name="ContentInfo.findByTheColumnInfoColumninfoId",query="select a from ContentInfo a where a.columnInfo.columninfoId= ?1 ")
	 * @param columninfoId
	 * @return
	 */
	List<ContentInfo> findByTheColumnInfoColumninfoId(int columninfoId);
	
	/**
	 * 保存contentInfo
	 * @param contentInfo
	 * @return
	 */
	public ContentInfo save(ContentInfo contentInfo);
	
	/**
	 * @Modifying
	 * @Query(value="update ContentInfo info set info.conname=?1 , info.conbody=?2 , info.columnInfo.columninfoId=?3 where info.contentId=?4 ")
	 * @param conname
	 * @param conbody
	 * @param columninfoId
	 * @param contentId
	 * @return
	 */
	int modify(String conname,String conbody,int columninfoId,int contentId);
	
	
	
	Page<ContentInfo> findAll(Pageable pageable);
}
