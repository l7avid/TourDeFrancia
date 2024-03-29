package com.tourdefrancia.TourDeFrancia.usecases.cyclist;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.mappers.CyclistMapper;
import com.tourdefrancia.TourDeFrancia.repository.ICyclistRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpdateCyclist {

    private final ICyclistRepo repository;
    private final CyclistMapper mapper;

    public UpdateCyclist(ICyclistRepo repository, CyclistMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<CyclistDto> updateCyclist(CyclistDto cyclistDto){
        return repository.save(mapper.toCyclist(cyclistDto)).map(cyclist -> mapper.toCyclistDto(cyclist));
    }
}
