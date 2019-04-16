package com.vsk.service;

import com.vsk.dto.EventDto;
import com.vsk.entity.Event;
import com.vsk.entity.User;
import com.vsk.repository.EventRepository;
import com.vsk.repository.UserRepository;
import com.vsk.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

	private final EventRepository eventRepository;
	private final UserRepository userRepository;

	@Autowired
	public EventService(EventRepository eventRepository, UserRepository userRepository) {
		this.eventRepository = eventRepository;
		this.userRepository = userRepository;
	}

	public Long addEvent(Long userId, String dateTime, String type, String description) {
		User userFromDb = userRepository.findById(userId).orElse(null);
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime.replace(" ", "T"));
		Event event = new Event(userFromDb, localDateTime, type, description);

		eventRepository.save(event);

		return event.getId();
	}

	public List<EventDto> findEventsByUserIdAndDate(Long userId, String dateTime) {
		return ObjectMapperUtils.mapAll(eventRepository.findEventsByUserIdAndLocalDateTimeGreaterThanEqual(
				userId, LocalDateTime.parse(dateTime.replace(" ", "T"))), EventDto.class);
	}

	public List<EventDto> findAllEvents() {
		return ObjectMapperUtils.mapAll(eventRepository.findAll(), EventDto.class);
	}
}
