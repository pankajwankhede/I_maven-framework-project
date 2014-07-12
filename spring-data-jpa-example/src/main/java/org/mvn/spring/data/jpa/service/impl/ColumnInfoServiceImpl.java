package org.mvn.spring.data.jpa.service.impl;

import java.util.List;

import org.mvn.spring.data.jpa.model.ColumnInfo;
import org.mvn.spring.data.jpa.repository.ColumnInfoRepository;
import org.mvn.spring.data.jpa.service.ColumnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="columnInfoServiceImpl")
@Transactional
public class ColumnInfoServiceImpl implements ColumnInfoService {
	
	@Autowired
	private ColumnInfoRepository columnInfoRepository;
	
	
	@Override
	public ColumnInfo findByColumninfoId(int columninfoId) {
		return columnInfoRepository.findByColumninfoId(columninfoId);
	}

	@Override
	public List<ColumnInfo> findByColnameLike(String colname) {
		return columnInfoRepository.findByColnameLike(colname);
	}

	@Override
	public List<ColumnInfo> findByColnameLike(String colname, Pageable pageable) {
		return columnInfoRepository.findByColnameLike(colname, pageable);
	}

	@Override
	public ColumnInfo save(ColumnInfo columnInfo) {
		return columnInfoRepository.save(columnInfo);
	}

	@Override
	public void deleteById(ColumnInfo columnInfo) {
		columnInfoRepository.delete(columnInfo);
	}

	@Override
	public int update(String colname, int columninfoId) {
		return columnInfoRepository.update(colname, columninfoId);
	}

	@Override
	public ColumnInfo findByTheColumninfoId(int columninfoId) {
		return columnInfoRepository.findByTheColumninfoId(columninfoId);
	}


}
