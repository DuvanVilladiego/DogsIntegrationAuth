package com.example.app.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import com.example.app.dto.DogsDTO;
import com.example.app.dto.GeneralResponseWithoutTokenDTO;

public interface DogController {

	public ResponseEntity<GeneralResponseWithoutTokenDTO<ArrayList<DogsDTO>>> getDogs(int page);
	
}
