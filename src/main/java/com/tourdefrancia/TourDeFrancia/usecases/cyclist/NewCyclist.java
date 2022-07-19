package com.tourdefrancia.TourDeFrancia.usecases.cyclist;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.mappers.CyclistMapper;
import com.tourdefrancia.TourDeFrancia.repository.ICyclistRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Validated
public class NewCyclist {

    private final ICyclistRepo repository;
    private final CyclistMapper mapper;

    public NewCyclist(ICyclistRepo repository, CyclistMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<CyclistDto> validateCyclistCode(CyclistDto cyclistDto){
        String cyclistCodeIncoming = cyclistDto.getCyclistCode();
        return repository.existsByCyclistCode(cyclistCodeIncoming).flatMap(aBoolean -> {
            if(Boolean.TRUE.equals(aBoolean)){
                return Mono.error(() -> new Exception("The given cyclist code is already in use"));
            }
            return repository.save(mapper.toCyclist(cyclistDto)).map(cyclist -> mapper.toCyclistDto(cyclist));
        });
    };



    public Mono<CyclistDto> newCyclist(@Valid CyclistDto cyclistDto){
            return validateCyclistCode(cyclistDto).flatMap(cyclistDto1 -> repository.save(mapper.toCyclist(cyclistDto)).map(cyclist -> mapper.toCyclistDto(cyclist)));
    }
}