/**
 * 
 */
package com.example.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author duvan
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponseWithoutTokenDTO<T> {
	private boolean status;
	private String message;
	private T data;
	private int numberOfPages;
	private int currentPage;
}
