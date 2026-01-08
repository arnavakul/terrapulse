package com.terrapulse.backend.repository;

import com.terrapulse.backend.model.EnvironmentalMetrics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnvironmentalMetricsRepository extends JpaRepository <EnvironmentalMetrics,Long> {
    List<EnvironmentalMetrics> findByCompanyId(Long companyId);

    Optional<EnvironmentalMetrics> findTopByCompanyIdAndMetricNameOrderByTimePeriodDesc(Long companyId, String metricName);
}
