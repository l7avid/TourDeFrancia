package com.tourdefrancia.TourDeFrancia.usecases.cyclist;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.mappers.CyclistMapper;
import com.tourdefrancia.TourDeFrancia.repository.ICyclistRepo;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GetCyclistsByCountry {

    private final ICyclistRepo repository;
    private final CyclistMapper mapper;

    public GetCyclistsByCountry(ICyclistRepo repository, CyclistMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    

    public Flux<CyclistDto> getCyclistsByCountry(String country){
        return repository.findByCountry(country).map(cyclist -> mapper.toCyclistDto(cyclist))
                .switchIfEmpty(Mono.error(() -> new Exception("Not able to find cyclists with the given country")));
    }
}
