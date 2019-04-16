package com.vsk.controller;

import com.vsk.entity.Event;
import com.vsk.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

	private final EventService eventService;

	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping("/all")
	@ResponseBody
	public List<Event> getEvents() {
		return eventService.findAllEvents();
	}

	@GetMapping
	@ResponseBody
	public List<Event> getEventByIdAndDate(@RequestBody Event event) {

		return eventService.findEventsByUserIdAndDate(event.getUserId(), event.getDate());
	}

	@PostMapping("/add")
	@ResponseBody
	public Long addEvent(@RequestBody Event event) {
		return eventService.addEvent(event.getUserId(), event.getDate(), event.getType(), event.getDescription());
	}
}
