package org.spring.jdbc.tutorials.dao;

import java.util.List;

import org.spring.jdbc.tutorials.model.Contact;

public interface ContactDAO {
	
	public void saveOrUpdate(Contact contact);
    
    public void delete(int contactId);
     
    public Contact get(int contactId);
     
    public List<Contact> list();
	
}
