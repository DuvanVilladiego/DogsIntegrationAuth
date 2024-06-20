package com.example.app.service.impl;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.app.client.AbstractClient;
import com.example.app.dto.DogsDTO;
import com.example.app.service.IntegrationDogService;

@Service
public class IntegrationDogServiceImpl extends AbstractClient implements IntegrationDogService {
	
	private static final Logger LOG = LoggerFactory.getLogger(IntegrationDogServiceImpl.class);

	protected IntegrationDogServiceImpl(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@Override
	public ArrayList<DogsDTO> getDogsWithImage() {
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		JSONObject responseJson = new JSONObject(response.getBody());
		JSONObject message = responseJson.getJSONObject("message");
		ArrayList<DogsDTO> dogsList = new ArrayList<>();
		for (String breed : message.keySet()) {
			JSONArray subBreeds = message.getJSONArray(breed);

			if (subBreeds.length() > 0) {
				for (int i = 0; i < subBreeds.length(); i++) {
					DogsDTO dog = new DogsDTO();
					dog.setName(subBreeds.getString(i));
					dog.setImage(getImage(String.format("%s/%s", breed, subBreeds.getString(i))));
					dogsList.add(dog);
				}
			}
			DogsDTO dog = new DogsDTO();
			dog.setName(breed);
			dog.setImage(getImage(breed));
			dogsList.add(dog);
		}
		return dogsList;
	}

	private String getImgUrl(String breed) {
		return String.format(urlImg, breed);
	}

	private String getImage(String dogBreed) {
		String breedImageUrl = getImgUrl(dogBreed);
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(breedImageUrl, String.class);
			JSONObject data = new JSONObject(response.getBody());
			JSONArray images = data.getJSONArray("message");
			return images.get(0).toString();
		} catch (Exception e) {
			return "";
		}
	}
}
