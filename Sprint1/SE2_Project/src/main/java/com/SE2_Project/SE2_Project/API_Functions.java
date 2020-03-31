package com.SE2_Project.SE2_Project;

import java.util.List;
import account_management.*;
import account_system.*;
import admin_functions.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class API_Functions {


	
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public String getAllUsers(String token)
	{
		String response="";
		AdminMenuInterface adminMenuInterface=new AdminMenuInterface();
		List<List<String>> allUsers =adminMenuInterface.getAllUsers(token);
		
		Gson json = new Gson();
		response=json.toJson(allUsers);
		return response;
	}
	
	@RequestMapping(value = "/normalUserSignUp", method = RequestMethod.POST)
	public String normalUserSignUp(String email,String userName,String password)
	{
		String response="";
		
		SignUpInterface signUpInterface =new SignUpInterface();
		boolean signedUp =signUpInterface.normalUserSignUp(email, userName, password);
		Gson json = new Gson();
		response=json.toJson(signedUp);
		return response;
	}
	
	@RequestMapping(value = "/shopOwnerSignUp", method = RequestMethod.POST)
	public String shopOwnerSignUp(String email,String userName,String password)
	{
		String response="";
		
		SignUpInterface signUpInterface =new SignUpInterface();
		boolean signedUp =signUpInterface.shopOwnerSignUp(email, userName, password);
		Gson json = new Gson();
		response=json.toJson(signedUp);
		return response;
	}
	
	@RequestMapping(value = "/deleteNormalUserAccount", method = RequestMethod.GET)
	public String deleteNormalUserAccount(String token,String email)
	{
		String response="";
		
		DeleteUserInterface deleteUserInterface=new DeleteUserInterface();
		boolean deleted =deleteUserInterface.deleteNormalUserAccount(token, email);
		Gson json = new Gson();
		response=json.toJson(deleted);
		return response;
	}
	

	@RequestMapping(value = "/deleteShopOwnerAccount", method = RequestMethod.GET)
	public String deleteShopOwnerAccount(String token,String email)
	{
		String response="";
		boolean deleted = false;
		
		DeleteUserInterface deleteUserInterface=new DeleteUserInterface();
		deleteUserInterface.deleteShopOwnerAccount(token, email);
		
		Gson json = new Gson();
		response=json.toJson(deleted);
		return response;
	}
	
	@RequestMapping(value = "/deleteAdminAccount", method = RequestMethod.GET)
	public String deleteAdminAccount(String token,String email)
	{
		String response="";
	
		
		DeleteUserInterface deleteUserInterface=new DeleteUserInterface();
		boolean deleted = deleteUserInterface.deleteAdminAccount(token, email);
		Gson json = new Gson();
		response=json.toJson(deleted);
		return response;
	}
	
	
	@RequestMapping(value = "/normalUserLogOut", method = RequestMethod.GET)
	public String normalUserLogOut(String token)
	{
		String response="";
		
		LogOutInterface logOutInterface =new LogOutInterface();
		boolean loggedOut =logOutInterface.normalUserLogOut(token);
		Gson json = new Gson();
		response=json.toJson(loggedOut);
		return response;
	}
	@RequestMapping(value = "/shopOwnerLogOut", method = RequestMethod.GET)
	public String shopOwnerLogOut(String token)
	{
		String response="";
		
		LogOutInterface logOutInterface =new LogOutInterface();
		boolean loggedOut =logOutInterface.shopOwnerLogOut(token);
		Gson json = new Gson();
		response=json.toJson(loggedOut);
		return response;
	}

	@RequestMapping(value = "/adminLogOut", method = RequestMethod.GET)
	public String adminLogOut(String token)
	{
		String response="";
		
		
		LogOutInterface logOutInterface =new LogOutInterface();
		boolean loggedOut =logOutInterface.adminLogOut(token);
		
		Gson json = new Gson();
		response=json.toJson(loggedOut);
		return response;
	}

	@RequestMapping(value = "/normalUserLogIn", method = RequestMethod.POST)
	public String normalUserLogIn(String email,String password)
	{
		String response="";
		
		LogInInterface logInInterface=new LogInInterface();
		String token=logInInterface.normalUserLogIn(email, password);
		Gson json = new Gson();
		response=json.toJson(token);
		return response;
	}
	@RequestMapping(value = "/shopOwnerLogIn", method = RequestMethod.POST)
	public String shopOwnerLogIn(String email,String password)
	{
		String response="";
		
		
		LogInInterface logInInterface=new LogInInterface();
		String token=logInInterface.shopOwnerLogIn(email, password);

		Gson json = new Gson();
		response=json.toJson(token);
		return response;
	}
	@RequestMapping(value = "/adminLogIn", method = RequestMethod.POST)
	public String adminLogIn(String email,String password)
	{
		String response="";
		
		
		LogInInterface logInInterface=new LogInInterface();
		
		String token=logInInterface.adminLogIn(email, password);

		System.out.println(token);
		Gson json = new Gson();
		response=json.toJson(token);
		return response;
	}
}
