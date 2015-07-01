package com.jcell.controller.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.jcell.persistense.api.response.BasicResponse;

@Controller
@RequestMapping(value="/transaction/")
public class Transaction {
	
	

	@RequestMapping(value = "action", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	public @ResponseBody String buyOrSell(HttpServletRequest request,@RequestBody String json) {
		String token=request.getHeader("X-Token");
		String transactionType=request.getHeader("X-TransactionType");
		Gson gson=new Gson();
		BasicResponse response=new BasicResponse();
		if(!token.isEmpty() && !transactionType.isEmpty()){
			response.setStatusDescription(HttpStatus.OK);
			response.setCaused("Data Queued. Please Wait To Be Proccessed");
			//TO DO put the data to queue to be processed by core
		}else{
			response.setStatusDescription(HttpStatus.NOT_ACCEPTABLE);
			response.setCaused("Parameter Required Not Found. Please Read Documentations.");
		}
		return gson.toJson(response);
	}
	
	@RequestMapping(value = "balance", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String getBalance(HttpServletRequest request) {
		return "";
	}
	
	@RequestMapping(value = "report/{datestart}/{dateend}", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String Report(HttpServletRequest request,@PathVariable String datestart,@PathVariable String dateend) {
		return "";
	}
}
