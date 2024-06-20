package com.example.app.service.impl;

import java.io.ByteArrayOutputStream;
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
import com.example.app.dto.DogsListWithPagesDTO;
import com.example.app.service.IntegrationDogService;
import com.example.app.utils.Base64Img;
import com.example.app.utils.Constants;
import com.example.app.utils.DownloadImg;

@Service
public class IntegrationDogServiceImpl extends AbstractClient implements IntegrationDogService {

	private static final Logger LOG = LoggerFactory.getLogger(IntegrationDogServiceImpl.class);

	protected IntegrationDogServiceImpl(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@Override
	public DogsListWithPagesDTO getDogsWithImage(int page) {
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		JSONObject responseJson = new JSONObject(response.getBody());
		JSONObject message = responseJson.getJSONObject("message");
		ArrayList<DogsDTO> dogsList = new ArrayList<>();
        ArrayList<DogsDTO> paginatedDogs = new ArrayList<>();
		LOG.info("SETTING BREEDS DATA");
		for (String breed : message.keySet()) {
			JSONArray subBreeds = message.getJSONArray(breed);
			if (subBreeds.length() > 0) {
				for (int i = 0; i < subBreeds.length(); i++) {
					DogsDTO dog = new DogsDTO();
					dog.setName(String.format("%s - %s", breed, subBreeds.getString(i)));
					dogsList.add(dog);
				}
			}
			DogsDTO dog = new DogsDTO();
			dog.setName(breed);
			dogsList.add(dog);
		}
		LOG.info("SETTING BREEDS DATA DONE");
        int totalDogs = dogsList.size();
        int pageSize = 5;
        int totalPages = (int) Math.ceil((double) totalDogs / pageSize);
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(page * pageSize, totalDogs);
        for (int i = startIndex; i < endIndex; i++) {
            DogsDTO dog = dogsList.get(i);
            String dogBreed = dog.getName().replace(" - ", "/");
            String base64Image = getImage(dogBreed);
            dog.setImage(base64Image);
            paginatedDogs.add(dog);
        }
		return new DogsListWithPagesDTO(paginatedDogs, totalPages, page);
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
			ByteArrayOutputStream bufferImg = DownloadImg.getImages(images.get(0).toString());
			return Base64Img.toBase64Img(bufferImg, images.get(0).toString());
		} catch (Exception e) {
			LOG.error(String.format(Constants.NOT_FOUND_IMG) + dogBreed);
			return "";
		}
	}
}
