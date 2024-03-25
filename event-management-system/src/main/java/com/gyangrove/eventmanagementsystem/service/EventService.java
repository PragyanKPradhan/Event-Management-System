package com.gyangrove.eventmanagementsystem.service;

import com.gyangrove.eventmanagementsystem.dto.EventDto;
import com.gyangrove.eventmanagementsystem.dto.EventResponse;
import com.gyangrove.eventmanagementsystem.dto.WeatherDto;
import com.gyangrove.eventmanagementsystem.entity.Event;
import com.gyangrove.eventmanagementsystem.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private EventRepository eventRepository;
    private WeatherService weatherService;
    private DistanceCalculationService distanceService;

    public String addEvents(MultipartFile file) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean skipFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("[H][HH]:mm:ss");
                Event event = new Event();
                event.setEventName(data[0]);
                event.setCityName(data[1]);
                event.setDate(LocalDate.parse(data[2], dateFormatter));
                event.setTime(LocalTime.parse(data[3], timeFormatter));
                event.setLatitude(Double.parseDouble(data[4]));
                event.setLongitude(Double.parseDouble(data[5]));
                eventRepository.save(event);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to add events", e);
        }
        return "Events added successfully";
    }

    public EventResponse findEvents(LocalDate startDate, double longitude, double latitude) {
        LocalDate endDate = startDate.plusDays(14);
        Page<Event> eventPage = eventRepository.findByDateBetweenOrderByDateAsc(startDate, endDate,
                PageRequest.of(1, 10));

        List<Event> events = eventPage.getContent();
        List<EventDto> eventDtos = new ArrayList<>();
        for (Event event : events) {
            eventDtos.add(mapToEventDto(event, longitude, latitude));
        }
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEvents(eventDtos);
        eventResponse.setPage(eventPage.getNumber());
        eventResponse.setPageSize(eventPage.getSize());
        eventResponse.setTotalEvents(eventPage.getTotalElements());
        eventResponse.setTotalPages(eventPage.getTotalPages());
        return eventResponse;
    }

    public EventDto mapToEventDto(Event event, double longitude, double latitude) {
        double distance = distanceService.calculateDistance(latitude, longitude, event.getLatitude(), event.getLongitude());
        WeatherDto weather = weatherService.getWeather(event.getCityName(), event.getDate());

        return EventDto.builder()
                .distance_km(distance)
                .weather(weather.getWeather())
                .city_name(event.getCityName())
                .event_name(event.getEventName())
                .date(event.getDate())
                .build();
    }
}
