package org.mvn.spring.data.jpa.service.impl;

import java.util.List;

import org.mvn.spring.data.jpa.model.ContentInfo;
import org.mvn.spring.data.jpa.repository.ContentInfoRepository;
import org.mvn.spring.data.jpa.service.ContentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="contentInfoServiceImpl")
@Transactional
public class ContentInfoServiceImpl implements ContentInfoService {
	
	@Autowired
	private ContentInfoRepository contentInfoRepository;
	
	@Override
	public ContentInfo findByContentId(int contentId) {
		return contentInfoRepository.findByContentId(contentId);
	}

	@Override
	public List<ContentInfo> findByConnameLike(String conname) {
		return contentInfoRepository.findByConnameLike(conname);
	}

	@Override
	public List<ContentInfo> findByConnameLike(String conname, Pageable pageable) {
		return contentInfoRepository.findByConnameLike(conname, pageable);
	}

	@Override
	public ContentInfo queryById(int contentId) {
		return contentInfoRepository.queryById(contentId);
	}

	/**
	 * 属性嵌套查询
	 * @param columninfoId  ContentInfo->columnInfo->columninfoId
	 * @return
	 */
	@Override
	public List<ContentInfo> findByColumnInfoColumninfoId(int columninfoId) {
		return contentInfoRepository.findByColumnInfoColumninfoId(columninfoId);
	}

	@Override
	public List<ContentInfo> findByColId(int columninfoId) {
		return contentInfoRepository.findByColId(columninfoId);
	}

	@Override
	public List<ContentInfo> findByTheColumnInfoColumninfoId(int columninfoId) {
		return contentInfoRepository.findByTheColumnInfoColumninfoId(columninfoId);
	}

	@Override
	public ContentInfo save(ContentInfo contentInfo) {
		return contentInfoRepository.save(contentInfo);
	}
	
	/**
	 * @Modifying
	 * @Query(value="update ContentInfo info set info.conname=?1 , info.conbody=?2 , info.columnInfo.columninfoId=?3 where info.contentId=?4 ")
	 * @param conname
	 * @param conbody
	 * @param columninfoId
	 * @param contentId
	 * @return
	 */
	@Override
	public int modify(String conname, String conbody, int columninfoId,
			int contentId) {
		return contentInfoRepository.modify(conname, conbody, columninfoId, contentId);
	}

	@Override
	public Page<ContentInfo> findAll(Pageable pageable) {
		return contentInfoRepository.findAll(pageable);
	}
}
