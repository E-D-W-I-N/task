package com.vsk.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private User user;
	private LocalDateTime localDateTime;
	private String type;
	private String description;

	public Event() {}

	public Event(User user, LocalDateTime localDateTime, String type, String description) {
		this.user = user;
		this.localDateTime = localDateTime;
		this.type = type;
		this.description = description;
	}
}
