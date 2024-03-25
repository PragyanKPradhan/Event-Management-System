package com.gyangrove.eventmanagementsystem.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EventDto {
	private String event_name, city_name, weather;
	private LocalDate date;
	private double distance_km;
}
