package com.ecommhub.seasonal;

import com.ecommhub.error.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seasonal")
public class SeasonalController {
    private final SeasonalService seasonalService;

    public SeasonalController(SeasonalService seasonalService) {
        this.seasonalService = seasonalService;
    }

    @GetMapping
    public List<Seasonal> fetchSeasonals(){
        return seasonalService.fetchSeasonals();
    }
    @PostMapping
    public Seasonal saveSeasonal(@RequestBody() SeasonalDTO seasonalDTO){
        return seasonalService.saveSeasonal(seasonalDTO);
    }
    @PutMapping("{id}")
    public Seasonal updateSeasonal(@PathVariable("id") Long seasonalId, @RequestBody() SeasonalDTO seasonalDTO) throws NotFoundException {
        return seasonalService.updateSeasonal(seasonalId, seasonalDTO);
    }
    @DeleteMapping("{id}")
    public Seasonal deleteSeasonal(@PathVariable("id") Long seasonalId) throws NotFoundException {
        return seasonalService.deleteSeasonal(seasonalId);
    }
}
