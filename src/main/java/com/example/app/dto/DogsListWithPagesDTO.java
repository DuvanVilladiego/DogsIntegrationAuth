package com.example.app.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DogsListWithPagesDTO {
	private ArrayList<DogsDTO> dogsList;
	private int numberOfPages;
	private int currentPage;
}
