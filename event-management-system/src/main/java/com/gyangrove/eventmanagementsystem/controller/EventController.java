package com.gyangrove.eventmanagementsystem.controller;
import com.gyangrove.eventmanagementsystem.dto.EventResponse;
import com.gyangrove.eventmanagementsystem.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/upload")
    public String uploadEvents(@RequestParam("file") MultipartFile file) {
        return eventService.addEvents(file);
    }

    @GetMapping("/find")
    public EventResponse findEvents(@RequestParam double longitude,
                                    @RequestParam double latitude,
                                    @RequestParam LocalDate date) {
        return eventService.findEvents(date, longitude, latitude);
    }
}
