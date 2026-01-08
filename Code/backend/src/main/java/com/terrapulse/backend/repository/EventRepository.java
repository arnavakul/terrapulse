package com.terrapulse.backend.repository;

import com.terrapulse.backend.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository <Events, Long>{
}
