package com.studerw.spring;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.studerw.spring.model.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/testContext.xml")
@TransactionConfiguration(defaultRollback = true)
public class HibernateTest {
    private static final Logger log = Logger.getLogger(HibernateTest.class);
    @Autowired
    SessionFactory sessionFactory;


    @Test
    @Transactional
    public void createAccount() {
        Session session = sessionFactory.getCurrentSession(); // not part of a transaction, so we need to open a session manually
        Account account = new Account();
        account.setName("blah");
        account.setCashBalance(12.23);
        long id = (Long)session.save(account);
        log.debug("saved new account, id=" + id);
    }

    @Test @Transactional
    public void updateAccount() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Account a");
        Account a = (Account) query.uniqueResult();
        log.debug(a);
    }
}
