package com.terrapulse.backend.repository;

import com.terrapulse.backend.model.SocialMetrics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SocialMetricsRepository extends JpaRepository<SocialMetrics, Long> {
    List<SocialMetrics> findByCompanyId(Long companyId);

    Optional<SocialMetrics> findTopByCompanyIdAndMetricNameOrderByTimePeriodDesc(Long companyId, String metricName);
}
