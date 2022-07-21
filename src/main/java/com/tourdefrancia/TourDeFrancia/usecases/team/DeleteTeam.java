package com.tourdefrancia.TourDeFrancia.usecases.team;

import com.tourdefrancia.TourDeFrancia.repository.ITeamRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteTeam {

    private final ITeamRepo repository;

    public DeleteTeam(ITeamRepo repository) {
        this.repository = repository;
    }

    public Mono<Void> deleteTeam(String id){
        return repository.findById(id)
                .switchIfEmpty(Mono.error(() -> new Exception("Not able to find a team with the given id")))
                .flatMap(team -> repository.deleteById(team.getId()));
    }
}
