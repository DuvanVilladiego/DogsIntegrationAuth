package com.example.app.service;

import java.util.ArrayList;

import com.example.app.dto.DogsDTO;
import com.example.app.dto.GeneralResponseWithoutTokenDTO;

public interface DogService {
	
	public GeneralResponseWithoutTokenDTO<ArrayList<DogsDTO>> getbreeds(int page);
	
}
