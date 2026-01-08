package com.terrapulse.backend.repository;

import com.terrapulse.backend.model.Target;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TargetRepository extends JpaRepository<Target, Long> {
    List<Target> findByCompanyId(Long companyId);
}
