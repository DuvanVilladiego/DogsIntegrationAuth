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
public class GeneralResponseWithTokenDTO {

	private boolean status;
	private String message;
	private Object data;
	private String token;
}
