package com.vsk.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EventDto {

	@NotNull(message = "Please fill user's id")
	private Long userId;

	@NotBlank(message = "Date and time can't be null")
	private String localDateTime;

	@NotBlank(message = "Event's type can't be null")
	private String type;

	@NotBlank(message = "Event's description can't be null")
	@Length(max = 200, message = "Description too long")
	private String description;
}
