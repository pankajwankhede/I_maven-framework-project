package org.mvn.spring.data.jpa.repository;

import java.util.List;

import org.mvn.spring.data.jpa.model.ColumnInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ColumnInfoRepository extends PagingAndSortingRepository<ColumnInfo, Integer> {
	
	/**
	 * 根据栏目id查询栏目
	 * @param columninfoId
	 * @return
	 */
	ColumnInfo findByColumninfoId(int columninfoId);
	
	/**
	 * 根据栏目名称模糊查询
	 * @param colname
	 * @return
	 */
	List<ColumnInfo> findByColnameLike(String colname);
	
	/**
	 * 根据栏目名称模糊查询 支持分页
	 * @param colname
	 * @param pageable
	 * @return
	 */
	List<ColumnInfo> findByColnameLike(String colname,Pageable pageable);
	
	/**
	 * 根据栏目id修改栏目名称
	 * @param colname
	 * @param columninfoId
	 * @return
	 */
	@Modifying
	@Query("update org.mvn.spring.data.jpa.model.ColumnInfo a set a.colname=?1 where a.columninfoId=?2")
	int update(String colname,int columninfoId);
	
	
	/**
	 * 根据@NamedQuery(name="ColumnInfo.findByTheColumninfoId",query=" from ColumnInfo a where a.columninfoId = ?1 ")
	 * @param columninfoId
	 * @return
	 */
	ColumnInfo findByTheColumninfoId(int columninfoId);
	
}
