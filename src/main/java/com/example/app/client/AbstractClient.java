package com.example.app.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractClient {

	@Autowired
	protected final RestTemplate restTemplate;
    
    @Value("${DOGS-URL-BREEDS}")
    protected String url;

    @Value("${DOGS-URL-BREEDS-IMAGES}")
    protected String urlImg;

    @Autowired
    protected AbstractClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
}
