package com.tourdefrancia.TourDeFrancia.usecases.team;

import com.tourdefrancia.TourDeFrancia.Dto.TeamDto;
import com.tourdefrancia.TourDeFrancia.mappers.TeamMapper;
import com.tourdefrancia.TourDeFrancia.repository.ITeamRepo;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Validated
public class NewTeam {

    private final ITeamRepo repository;
    private final TeamMapper mapper;

    public NewTeam(ITeamRepo repository, TeamMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<TeamDto> validateTeamCode(TeamDto teamDto){
        String teamCodeIncoming = teamDto.getTeamCode();
        return repository.existsByTeamCode(teamCodeIncoming)
                .flatMap(aBoolean -> {
                    if(Boolean.TRUE.equals(aBoolean)){
                        return Mono.error(() -> new Exception("The given team code is already in use"));
                    }
                    return repository.save(mapper.toTeam(teamDto)).map(team -> mapper.toTeamDto(team));
                });
    }

    public Mono<TeamDto> newTeam(@Valid TeamDto teamDto){
        return validateTeamCode(teamDto);
    }
}
