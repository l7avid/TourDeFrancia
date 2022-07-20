package com.tourdefrancia.TourDeFrancia.repository;

import com.tourdefrancia.TourDeFrancia.collections.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ICyclistRepo extends ReactiveMongoRepository<Cyclist, String> {

    Flux<Cyclist> findByTeamCode(String teamCode);

    Flux<Cyclist> findByCountry(String country);

    Mono<Cyclist> findByCyclistCode(String cyclistCode);

    Mono<Boolean> existsByCyclistCode(String cyclistCode);


}
