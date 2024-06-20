package com.example.app.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dto.DogsDTO;
import com.example.app.dto.GeneralResponseWithoutTokenDTO;
import com.example.app.service.DogService;
import com.example.app.service.IntegrationDogService;
import com.example.app.utils.Constants;

@Service
public class DogServiceImpl implements DogService {

	@Autowired
	private IntegrationDogService service;
	
	@Override
	public GeneralResponseWithoutTokenDTO<ArrayList<DogsDTO>> getbreeds() {
		try {
			ArrayList<DogsDTO> dogs = service.getDogsWithImage();
			return new GeneralResponseWithoutTokenDTO<ArrayList<DogsDTO>>(true, Constants.SUCCESS_MESSAGE, dogs);
		} catch (Exception e) {
			return new GeneralResponseWithoutTokenDTO<ArrayList<DogsDTO>>(false, e.getMessage(), null);
		}
	}

}
