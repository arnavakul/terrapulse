package com.terrapulse.backend.controller;

import com.terrapulse.backend.model.Events;
import com.terrapulse.backend.service.CompanyService;
import com.terrapulse.backend.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping
    public Events createEvent(@RequestBody Events events){
        return eventService.createEvent(events);
    }

    @GetMapping
    public List<Events> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Events getEvent(@PathVariable Long id){
        return eventService.getEventById(id);
    }

    @PutMapping("/{id}")
    public Events updateEvent(
            @PathVariable Long id,
            @RequestBody Events event
    ){
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
    }

}
