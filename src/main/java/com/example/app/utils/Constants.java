package com.example.app.utils;

public class Constants {
	
	//DOMAIN PATHS
	public static final String AUTHENTICATION_PATH = "api/authentication";
	public static final String DOGS_PATH = "api/dogs";
	
	//PATHS
	public static final String LOGIN_PATH = "login";
	public static final String REGISTER_PATH = "register";
	public static final String REFRESH_TOKEN_PATH = "refresh-token";
	public static final String BREEDS_PATH = "breeds";
	
	//PATHS MATCHERS
	public static final String LOGIN_MATCHER_PATH = String.format("%s/%s", AUTHENTICATION_PATH, LOGIN_PATH);
	public static final String REGISTER_MATCHER_PATH = String.format("%s/%s", AUTHENTICATION_PATH, REGISTER_PATH);
	public static final String REFRESH_TOKEN_MATCHER_PATH = String.format("%s/%s", AUTHENTICATION_PATH, REFRESH_TOKEN_PATH);

	//RESPONSE MESSAGES
	public static final String SUCCESS_MESSAGE = "SUCCESS REQUEST";
	public static final String USER_NOT_FOUND_MESSAGE = "USER NOT FOUND";
	public static final String USER_ALREADY_EXISTS_MESSAGE = "USER ALREADY EXIST WITH THAT EMAIL";
}
