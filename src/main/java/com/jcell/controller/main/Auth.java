package com.jcell.controller.main;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jcell.dao.AccountDao;
import com.jcell.persistense.api.response.BasicResponse;
import com.jcell.persistense.api.response.LoginResponse;
import com.jcell.persistense.api.retrieve.ChangePassword;
import com.jcell.persistense.api.retrieve.Login;
import com.jcell.persistense.db.postgresql.Account;
import com.jcell.service.db.AuthService;


@Controller
@RequestMapping(value="/auth/")
public class Auth {

	@Autowired AuthService authService;
	@Autowired AccountDao accountDao;
	
	@RequestMapping(value = "login", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	public @ResponseBody String login(HttpServletRequest request,@RequestBody String json) {
		LoginResponse response=new LoginResponse();
		Login login=new Login();
		Gson gson=new Gson();
		try {
			login=gson.fromJson(json, Login.class);
			Account account=authService.getAccountByUsernameAndPassword(login.getUsername(), login.getPassword());
			response.setStatusDescription(HttpStatus.OK);
			if(account==null){
				response.setCaused("Username or Password Not Found");
			}if(!account.getActivationStatus()){
				response.setCaused("This User Is Not Active Yet. Please Contact Your Provider to Send an Email For Activation Link");
			}else{
				account.setTokenNumber(UUID.randomUUID().toString());
				account.setDeviceId(login.getDeviceID());
				response.setToken(account.getTokenNumber());
				accountDao.save(account);
			}
		} catch (JsonSyntaxException e) {
			response.setStatusDescription(HttpStatus.EXPECTATION_FAILED);
			response.setCaused("Wrong Data Format");
		}
		return gson.toJson(response);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	public @ResponseBody String changePassword(HttpServletRequest request,@RequestBody String json) {
		LoginResponse response=new LoginResponse();
		ChangePassword changePassword=new ChangePassword();
		Gson gson=new Gson();
		try {
			changePassword=gson.fromJson(json, ChangePassword.class);
			Account account=authService.getAccountByUsernameAndPassword(changePassword.getUsername(), changePassword.getOldPassword());
			response.setStatusDescription(HttpStatus.OK);
			if(account==null){
				response.setCaused("Username or Password Not Found");
			}if(!account.getActivationStatus()){
				response.setCaused("This User Is Not Active Yet. Please Contact Your Provider to Send an Email For Activation Link");
			}else{
				account.setTokenNumber(UUID.randomUUID().toString());
				account.setDeviceId(changePassword.getDeviceID());
				account.setPassword(changePassword.getPassword());
				response.setToken(account.getTokenNumber());
				accountDao.save(account);
			}
		} catch (JsonSyntaxException e) {
			response.setStatusDescription(HttpStatus.EXPECTATION_FAILED);
			response.setCaused("Wrong Data Format");
		}
		return gson.toJson(response);
	}
	
	@RequestMapping(value = "refresh_token", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String refreshToken(HttpServletRequest request) {
		String token=request.getHeader("X-Token");
		LoginResponse response=new LoginResponse();
		Gson gson=new Gson();
		if(token.isEmpty()){
			response.setStatusDescription(HttpStatus.NOT_ACCEPTABLE);
			response.setCaused("Token Not Found");
		}else{
			Account account=authService.getAccountByToken(token);
			response.setStatusDescription(HttpStatus.OK);
			if(account==null){
				response.setStatusDescription(HttpStatus.NOT_FOUND);
				response.setCaused("Token Not Found");
			}else{
				account.setTokenNumber(UUID.randomUUID().toString());
				response.setToken(account.getTokenNumber());
				accountDao.save(account);
			}
		}
		return gson.toJson(response);
	}
		
	@RequestMapping(value = "logout", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String logout(HttpServletRequest request) {
		String token=request.getHeader("X-Token");
		BasicResponse response=new BasicResponse();
		Gson gson=new Gson();
		if(token.isEmpty()){
			response.setStatusDescription(HttpStatus.NOT_ACCEPTABLE);
			response.setCaused("Token Not Found");
		}else{
			Account account=authService.getAccountByToken(token);
			response.setStatusDescription(HttpStatus.OK);
			if(account==null){
				response.setStatusDescription(HttpStatus.NOT_FOUND);
				response.setCaused("Token Not Found");
			}else{
				account.setTokenNumber("");
				accountDao.save(account);
			}
		}
		return gson.toJson(response);
	}
}
