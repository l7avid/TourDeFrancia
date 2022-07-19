package com.tourdefrancia.TourDeFrancia.mappers;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.collections.Cyclist;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class CyclistMapper {

    private final ModelMapper mapper;

    public CyclistMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CyclistDto toCyclistDto(Cyclist cyclist){
        return mapper.map(cyclist, CyclistDto.class);
    }

    public Cyclist toCyclist(CyclistDto cyclistDto){
        return mapper.map(cyclistDto, Cyclist.class);
    }
}
