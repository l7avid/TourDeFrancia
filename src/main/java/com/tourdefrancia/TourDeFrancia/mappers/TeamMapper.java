package com.tourdefrancia.TourDeFrancia.mappers;

import com.tourdefrancia.TourDeFrancia.Dto.TeamDto;
import com.tourdefrancia.TourDeFrancia.collections.Team;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class TeamMapper {

    private final ModelMapper mapper;

    public TeamMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public TeamDto toTeamDto(Team team){
        return mapper.map(team, TeamDto.class);
    }

    public Team toTeam(TeamDto teamDto){
        return mapper.map(teamDto, Team.class);
    }
}
