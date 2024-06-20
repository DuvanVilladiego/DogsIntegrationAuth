package com.example.app.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadImg {
	
	private static final Logger LOG = LoggerFactory.getLogger(DownloadImg.class);

	public static ByteArrayOutputStream getImages(String imageUrl) {
		ByteArrayOutputStream outputStream = null;
		try {
			URL url = new URL(imageUrl);
			outputStream = new ByteArrayOutputStream();
			byte[] chunk = new byte[4096];
			int bytesRead;
			try (InputStream stream = url.openStream()) {
				while ((bytesRead = stream.read(chunk)) > 0) {
					outputStream.write(chunk, 0, bytesRead);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (final IOException e) {
					e.printStackTrace();
					LOG.error(String.format(Constants.ERR_DOWNLOAD_IMG, e.getMessage()));
				}
			}
		}
		return outputStream;
	}

}
