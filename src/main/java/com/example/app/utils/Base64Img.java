package com.example.app.utils;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Base64Img {
	
	private static final Logger LOG = LoggerFactory.getLogger(Base64Img.class);

	public static String toBase64Img(ByteArrayOutputStream outputStream) {
		try {
			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			return base64Image;
		} catch (Exception e) {
			LOG.error(String.format("ERROR PROCESSING THE IMAGE TO BASE64, ERROR: %s", e.getMessage()));
			return "";
		}
	}
}
