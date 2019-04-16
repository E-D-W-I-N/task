package com.vsk.controller;

import com.vsk.dto.EventDto;
import com.vsk.service.EventService;
import com.vsk.util.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventController {

	private final EventService eventService;

	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<EventDto>> getEvents() {
		return new ResponseEntity(eventService.findAllEvents(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<EventDto>> getEventByIdAndDate(@RequestBody EventDto eventDto) {
		return new ResponseEntity(
				eventService.findEventsByUserIdAndDate(eventDto.getUserId(), eventDto.getLocalDateTime()), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addEvent(@RequestBody @Valid EventDto eventDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
			return new ResponseEntity(errorsMap, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity(eventService.addEvent(eventDto.getUserId(), eventDto.getLocalDateTime(),
				eventDto.getType(), eventDto.getDescription()), HttpStatus.OK);
	}
}
