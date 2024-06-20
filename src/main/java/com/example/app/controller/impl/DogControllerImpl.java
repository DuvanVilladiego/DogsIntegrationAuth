package com.example.app.controller.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.controller.DogController;
import com.example.app.dto.DogsDTO;
import com.example.app.dto.GeneralResponseWithoutTokenDTO;
import com.example.app.service.DogService;
import com.example.app.utils.Constants;

@RestController
@RequestMapping(Constants.DOGS_PATH)
public class DogControllerImpl implements DogController {

	@Autowired
	private DogService service;
	
	@Override
	@GetMapping(Constants.BREEDS_PATH)
	public ResponseEntity<GeneralResponseWithoutTokenDTO<ArrayList<DogsDTO>>> getDogs(@PathVariable int page) {
		GeneralResponseWithoutTokenDTO<ArrayList<DogsDTO>> response = service.getbreeds(page);
		if (response.isStatus()) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}
