package com.jcell.service.db;

import com.jcell.persistense.db.postgresql.Account;

public interface AuthService {

	Boolean createAccount(Account account);
	Account getAccountByUsernameAndPassword(String username,String password);
	Account getAccountByToken(String token);
}
