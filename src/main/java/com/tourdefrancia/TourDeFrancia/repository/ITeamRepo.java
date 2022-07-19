package com.tourdefrancia.TourDeFrancia.repository;

import com.tourdefrancia.TourDeFrancia.collections.Team;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ITeamRepo extends ReactiveMongoRepository<Team, String> {

    Flux<Team> findByCountry(String teamCountry);

    Mono<Team> findByTeamName(String teamName);

    Mono<Team> findByTeamCode(String teamCode);
}
