package com.example.app.utils;

public class Constants {
	
	//ROLE
	public static final String ROLE_USER = "ROLE_USER";
	
	//DOMAIN PATHS
	public static final String AUTHENTICATION_PATH = "api/authentication";
	public static final String DOGS_PATH = "api/dogs";
	
	//PATHS
	public static final String LOGIN_PATH = "login";
	public static final String REGISTER_PATH = "register";
	public static final String REFRESH_TOKEN_PATH = "refresh-token";
	public static final String BREEDS_PATH = "breeds/{page}";
	
	//PATHS MATCHERS
	public static final String LOGIN_MATCHER_PATH = String.format("%s/%s", AUTHENTICATION_PATH, LOGIN_PATH);
	public static final String REGISTER_MATCHER_PATH = String.format("%s/%s", AUTHENTICATION_PATH, REGISTER_PATH);
	public static final String REFRESH_TOKEN_MATCHER_PATH = String.format("%s/%s", AUTHENTICATION_PATH, REFRESH_TOKEN_PATH);

	//RESPONSE MESSAGES
	public static final String SUCCESS_MESSAGE = "SUCCESS REQUEST";
	public static final String USER_NOT_FOUND_MESSAGE = "USER NOT FOUND";
	public static final String USER_ALREADY_EXISTS_MESSAGE = "USER ALREADY EXIST WITH THAT EMAIL";
	
	//INFO MESSAGES
	public static final String REFRESH_TOKEN_INIT = "INIT REFRESHING SERVICE";
	
	//ERRORS
	public static final String AUTH_ERR = "ERROR DURING ATHENTICATION";
	public static final String INVALID_TOKEN = "ERROR INVALID TOKEN";
	public static final String REFRESH_ERR = "ERROR DURING JWT REFRESH";
	public static final String ERR_PROCESS_IMG = "ERROR DURING IMAGE TO BASE64 PROCESS, ERROR: %s";
	public static final String ERR_DOWNLOAD_IMG = "ERROR DURING DOWNLOAD IMAGE, ERROR: %s";
	public static final String ERR_VALIDATE_TOKEN = "ERROR DURING JWT VALIDATION, ERROR: %s";
	public static final String ERR_GETTING_DOGS = "ERROR GETTING DOGS DATA, ERROR: %s";
	public static final String ERR_LOGIN = "ERROR DURING LOGIN PROCESS, ERROR: %s";
	public static final String ERR_REGISTER = "ERROR DURING REGISTER PROCESS, ERROR: %s";
	public static final String ERR_REFRESH = "ERROR DURING REFRESH TOKEN PROCESS, ERROR: %s";
	public static final String UNAUTH_USER_ERR = "USER NOT AUTHENTICATED";
	public static final String NOT_FOUND_IMG = "NOT FOUND IMAGE FOR: %s";
	
	//API-NAME
	public static final String LOGIN_AUTH_API = "login-auth-api";
	
}
