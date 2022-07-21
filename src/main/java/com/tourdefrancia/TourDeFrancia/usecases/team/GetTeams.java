package com.tourdefrancia.TourDeFrancia.usecases.team;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.Dto.TeamDto;
import com.tourdefrancia.TourDeFrancia.mappers.TeamMapper;
import com.tourdefrancia.TourDeFrancia.repository.ITeamRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetTeams {

    private final ITeamRepo repository;
    private final TeamMapper mapper;

    public GetTeams(ITeamRepo repository, TeamMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<TeamDto> getAllTeams(){
        return repository.findAll().map(team -> mapper.toTeamDto(team));
    }
}
