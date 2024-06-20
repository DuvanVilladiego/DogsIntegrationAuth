package com.example.app.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dto.DogsDTO;
import com.example.app.dto.DogsListWithPagesDTO;
import com.example.app.dto.GeneralResponseWithoutTokenDTO;
import com.example.app.service.DogService;
import com.example.app.service.IntegrationDogService;
import com.example.app.utils.Constants;

@Service
public class DogServiceImpl implements DogService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DogServiceImpl.class);

	@Autowired
	private IntegrationDogService service;
	
	@Override
	public GeneralResponseWithoutTokenDTO<ArrayList<DogsDTO>> getbreeds(int page) {
		try {
			DogsListWithPagesDTO dogs = service.getDogsWithImage(page);
			return new GeneralResponseWithoutTokenDTO<ArrayList<DogsDTO>>(true, Constants.SUCCESS_MESSAGE, dogs.getDogsList(), dogs.getNumberOfPages(), dogs.getCurrentPage());
		} catch (Exception e) {
			LOG.error(String.format(Constants.ERR_GETTING_DOGS, e.getMessage()));
			return new GeneralResponseWithoutTokenDTO<ArrayList<DogsDTO>>(false, e.getMessage(), null, 0, 0);
		}
	}

}
