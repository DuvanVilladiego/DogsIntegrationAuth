package com.example.app.service;

import com.example.app.dto.DogsListWithPagesDTO;

public interface IntegrationDogService {

	public DogsListWithPagesDTO getDogsWithImage(int page);
	
}
