package com.tourdefrancia.TourDeFrancia.usecases.cyclist;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.mappers.CyclistMapper;
import com.tourdefrancia.TourDeFrancia.repository.ICyclistRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetCyclistByCode {

    private final ICyclistRepo repository;
    private final CyclistMapper mapper;

    public GetCyclistByCode(ICyclistRepo repository, CyclistMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<CyclistDto> getCyclistByCode(String cyclistCode){
        return repository.findByCyclistCode(cyclistCode).map(cyclist -> mapper.toCyclistDto(cyclist))
                .switchIfEmpty(Mono.error(() -> new Exception("Not able to find a cyclist with the given code")));
    }
}
