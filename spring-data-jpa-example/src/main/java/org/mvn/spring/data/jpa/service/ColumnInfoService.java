package org.mvn.spring.data.jpa.service;

import java.util.List;

import org.mvn.spring.data.jpa.model.ColumnInfo;
import org.springframework.data.domain.Pageable;

public interface ColumnInfoService {
	
	ColumnInfo findByColumninfoId(int columninfoId);
	
	List<ColumnInfo> findByColnameLike(String colname);
	
	List<ColumnInfo> findByColnameLike(String colname,Pageable pageable);
	
	ColumnInfo save(ColumnInfo columnInfo);
	
	void deleteById(ColumnInfo columnInfo);
	
	int update(String colname,int columninfoId);
	
	ColumnInfo findByTheColumninfoId(int columninfoId);
	
	
}
