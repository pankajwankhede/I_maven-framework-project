package org.hibernate.search.jpa.example;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Administrator
 *
 */
public class IndexManger implements InitializingBean{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		//重建索引
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		
		fullTextEntityManager.createIndexer().startAndWait();
	}
}
