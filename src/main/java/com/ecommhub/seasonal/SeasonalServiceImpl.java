package com.ecommhub.seasonal;

import com.ecommhub.error.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeasonalServiceImpl implements SeasonalService {
    private final SeasonalRepository seasonalRepository;

    public SeasonalServiceImpl(SeasonalRepository seasonalRepository) {
        this.seasonalRepository = seasonalRepository;
    }

    @Override
    public List<Seasonal> fetchSeasonals() {
        return seasonalRepository.findAll();
    }

    @Override
    public Seasonal saveSeasonal(SeasonalDTO seasonalDTO) {
        Seasonal seasonal = Seasonal.builder()
                .name(seasonalDTO.name())
                .image(seasonalDTO.image())
                .build();
        return seasonalRepository.save(seasonal);
    }

    @Override
    public Seasonal updateSeasonal(Long seasonalId, SeasonalDTO seasonalDTO) throws NotFoundException {
        Optional<Seasonal> seasonal = seasonalRepository.findById(seasonalId);
        Seasonal db_seasonal = seasonal.orElseThrow(() -> new NotFoundException("Seasonal Not Found By Provided ID"));
        db_seasonal.setName(seasonalDTO.name());
        db_seasonal.setImage(seasonalDTO.image());
        return seasonalRepository.save(db_seasonal);
    }

    @Override
    public Seasonal deleteSeasonal(Long seasonalId) throws NotFoundException {
        Optional<Seasonal> seasonal = seasonalRepository.findById(seasonalId);
        Seasonal db_seasonal = seasonal.orElseThrow(() -> new NotFoundException("Seasonal Not Found By Provided ID"));
        seasonalRepository.deleteById(seasonalId);
        return db_seasonal;
    }
}
