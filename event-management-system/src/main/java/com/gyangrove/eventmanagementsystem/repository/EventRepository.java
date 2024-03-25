package com.gyangrove.eventmanagementsystem.repository;

import com.gyangrove.eventmanagementsystem.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EventRepository extends JpaRepository<Event, Long> {
	Page<Event> findByDateBetweenOrderByDateAsc(LocalDate startDate, LocalDate endDate, PageRequest pageRequest);
}
