package com.ecommhub.seasonal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonalRepository extends JpaRepository<Seasonal, Long> {
}
