package com.vsk.service;

import com.vsk.dto.EventDto;
import com.vsk.entity.Event;
import com.vsk.entity.User;
import com.vsk.repository.EventRepository;
import com.vsk.repository.UserRepository;
import com.vsk.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@Validated
public class EventService {

	private final EventRepository eventRepository;
	private final UserRepository userRepository;

	@Autowired
	public EventService(EventRepository eventRepository, UserRepository userRepository) {
		this.eventRepository = eventRepository;
		this.userRepository = userRepository;
	}

	public Long addEvent(Long userId, String dateTimeString, String type, String description) {
		User userFromDb = userRepository.findById(userId).orElse(null);
		if(userFromDb == null)
			throw new EntityNotFoundException("User not found");
		else {
			Event event = new Event(userFromDb, parseLocalDateTime(dateTimeString), type, description);
			eventRepository.save(event);

			return event.getId();
		}
	}

	private LocalDateTime parseLocalDateTime(String dateTimeString) throws DateTimeParseException {
		LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString.replace(" ", "T"));
		return localDateTime;
	}

	public List<EventDto> findEventsByUserIdAndDate(Long userId, String dateTimeString) {
		return ObjectMapperUtils.mapAll(eventRepository.findEventsByUserIdAndLocalDateTimeGreaterThanEqual(
				userId, parseLocalDateTime(dateTimeString)), EventDto.class);
	}

	public List<EventDto> findAllEvents() {
		return ObjectMapperUtils.mapAll(eventRepository.findAll(), EventDto.class);
	}
}
