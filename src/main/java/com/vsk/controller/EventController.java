package com.vsk.controller;

import com.vsk.dto.EventDto;
import com.vsk.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/event")
@Validated
public class EventController {

	private final EventService eventService;

	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<EventDto>> getEvents() {

		List<EventDto> events = eventService.findAllEvents();
		if (events.isEmpty())
			throw new EntityNotFoundException("There's no events");
		else
			return new ResponseEntity(events, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<EventDto>> getEventByIdAndDate(
			@NotNull(message = "Please fill user's id") @RequestParam Long userId,
			@NotBlank(message = "Date and time can't be null") @RequestParam String localDateTime) {

		List<EventDto> events = eventService.findEventsByUserIdAndDate(userId, localDateTime);
		if (events.isEmpty())
			throw new EntityNotFoundException("There's no events for this date or user");
		else
			return new ResponseEntity(events, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addEvent(@Valid @RequestBody EventDto eventDto) {

		return new ResponseEntity(eventService.addEvent(eventDto.getUserId(), eventDto.getLocalDateTime(),
				eventDto.getType(), eventDto.getDescription()), HttpStatus.OK);
	}
}
