package com.vsk.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table
@Data
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Date date;
	@NotBlank(message = "Please fill event type")
	private String type;
	@Length(max = 200, message = "Description too long")
	private String description;
}
