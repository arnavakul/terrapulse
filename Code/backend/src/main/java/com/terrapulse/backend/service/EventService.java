package com.terrapulse.backend.service;

import com.terrapulse.backend.model.Events;
import com.terrapulse.backend.repository.EventRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService ( EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public Events createEvent(Events events){
        return  eventRepository.save(events);
    }

    public List<Events> getAllEvents(){
        return eventRepository.findAll();
    }

    public Events getEventById(Long id){
        return eventRepository.findById(id).orElseThrow(()->new RuntimeException("Event not found"));
    }

    public Events updateEvent(Long id, Events updatedEvent){
        Events existing = getEventById(id);
        existing.setEventId(updatedEvent.getEventId());
        existing.setCategory(updatedEvent.getCategory());
        existing.setCompanyId(updatedEvent.getCompanyId());
        existing.setDate(updatedEvent.getDate());
        existing.setDescription(updatedEvent.getDescription());
        existing.setSeverity(updatedEvent.getSeverity());

        return eventRepository.save(existing);
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }

}
