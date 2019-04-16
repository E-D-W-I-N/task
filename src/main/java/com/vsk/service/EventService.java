package com.vsk.service;

import com.vsk.entity.Event;
import com.vsk.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventService {

	private final EventRepository eventRepository;

	@Autowired
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public Long addEvent(Long userId, Date date, String type, String description) {
		Event event = new Event();
		event.setUserId(userId);
		event.setDate(date);
		event.setType(type);
		event.setDescription(description);

		eventRepository.save(event);

		return event.getId();
	}

	public List<Event> findEventsByUserIdAndDate(Long userId, Date date) {
		return eventRepository.findEventsByUserIdAndDateGreaterThanEqual(userId, date);
	}

	public List<Event> findAllEvents() {
		return eventRepository.findAll();
	}
}
