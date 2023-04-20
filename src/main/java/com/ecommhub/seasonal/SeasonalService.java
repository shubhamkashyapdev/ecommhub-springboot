package com.ecommhub.seasonal;

import com.ecommhub.error.NotFoundException;

import java.util.List;

public interface SeasonalService {
    List<Seasonal> fetchSeasonals();

    Seasonal saveSeasonal(SeasonalDTO seasonalDTO);

    Seasonal updateSeasonal(Long seasonalId, SeasonalDTO seasonalDTO) throws NotFoundException;

    Seasonal deleteSeasonal(Long seasonalId) throws NotFoundException;
}
