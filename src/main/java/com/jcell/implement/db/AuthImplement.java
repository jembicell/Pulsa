package com.jcell.implement.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcell.dao.AccountDao;
import com.jcell.persistense.db.postgresql.Account;
import com.jcell.service.db.AuthService;

@Service
public class AuthImplement implements AuthService{

	@Autowired AccountDao accountDao;
	public Boolean createAccount(Account account) {
		try {
			accountDao.save(account);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Account getAccountByUsernameAndPassword(String username,String password) {
		return accountDao.getAccountByCredential(username, password);
	}

	public Account getAccountByToken(String token) {
		return accountDao.getAccountByToken(token);
	}

}
