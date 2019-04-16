package com.vsk.repository;

import com.vsk.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

	List<Event> findEventsByUserIdAndDateGreaterThanEqual(Long userId, Date date);
}
