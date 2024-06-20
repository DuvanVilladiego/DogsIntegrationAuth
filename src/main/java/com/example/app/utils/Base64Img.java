package com.example.app.utils;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64Img {
	
	private static final Logger LOG = LoggerFactory.getLogger(Base64Img.class);

	public static String toBase64Img(ByteArrayOutputStream outputStream, String imageUrl) {
		try {
			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			String baseImgFormated = String.format("data:image/%s;base64,%s", getFileExtension(imageUrl), base64Image);
			return baseImgFormated;
		} catch (Exception e) {
			LOG.error(String.format(Constants.ERR_PROCESS_IMG, e.getMessage()));
			return "";
		}
	}
	
	private static String getFileExtension(String fileName) {
        Pattern pattern = Pattern.compile("\\.([^.]+)$");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }
}
