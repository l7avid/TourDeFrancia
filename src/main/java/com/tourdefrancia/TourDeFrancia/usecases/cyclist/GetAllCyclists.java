package com.tourdefrancia.TourDeFrancia.usecases.cyclist;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.mappers.CyclistMapper;
import com.tourdefrancia.TourDeFrancia.repository.ICyclistRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllCyclists {

    private final ICyclistRepo repository;
    private final CyclistMapper mapper;

    public GetAllCyclists(ICyclistRepo repository, CyclistMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<CyclistDto> getAllCyclists(){
        return repository.findAll().map(cyclist -> mapper.toCyclistDto(cyclist));
    }
}
