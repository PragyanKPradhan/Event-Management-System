package com.gyangrove.eventmanagementsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EventResponse {
	private List<EventDto> events;
	private int page;
	private int pageSize;
	private long totalEvents;
	private int totalPages;
}
