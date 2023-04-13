package com.ecommhub.seasonal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeasonalRepositoryTest {
    @Autowired
    private SeasonalRepository seasonalRepository;

    @Test
    public void saveSeasonalCollection(){
        Seasonal season = Seasonal.builder()
                .name("Summer")
                .image("https://images/summer.jpg")
                .build();
        Seasonal db_season = seasonalRepository.save(season);
    }

    @Test
    public void fetchAllSeasons(){
        List<Seasonal> seasons = seasonalRepository.findAll();
        System.out.println(seasons);
    }
}