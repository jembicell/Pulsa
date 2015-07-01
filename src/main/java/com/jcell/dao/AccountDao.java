package com.jcell.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.jcell.persistense.db.postgresql.Account;
import com.jcell.util.GenerikDao;

@Component
public class AccountDao extends GenerikDao<Account>{

	public Account getAccountByCredential(String username,String password){
		Query query = sessionFactory.getCurrentSession().createQuery("from Account where username = :username AND password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		@SuppressWarnings("unchecked")
		List<Account> list = query.list();
	    return (list.isEmpty() ? null : list.get(0));
	}
	
	public Account getAccountByToken(String token){
		Query query = sessionFactory.getCurrentSession().createQuery("from Account where tokenNumber = :tokenNumber");
		query.setParameter("tokenNumber", token);
		@SuppressWarnings("unchecked")
		List<Account> list = query.list();
	    return (list.isEmpty() ? null : list.get(0));
	}
}
