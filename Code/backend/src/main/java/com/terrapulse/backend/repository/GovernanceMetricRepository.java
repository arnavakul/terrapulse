package com.terrapulse.backend.repository;

import com.terrapulse.backend.model.GovernanceMetrics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GovernanceMetricRepository
        extends JpaRepository<GovernanceMetrics, Long> {

    List<GovernanceMetrics> findByCompanyId(Long companyId);

    Optional<GovernanceMetrics>
    findTopByCompanyIdAndMetricNameOrderByTimePeriodDesc(
            Long companyId,
            String metricName
    );
}
