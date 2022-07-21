package com.tourdefrancia.TourDeFrancia.usecases.cyclist;

import com.tourdefrancia.TourDeFrancia.repository.ICyclistRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteCyclist {

    private final ICyclistRepo repository;

    public DeleteCyclist(ICyclistRepo repository) {
        this.repository = repository;
    }

    public Mono<Void> deleteCyclis(String id){
        return repository.findById(id)
                .switchIfEmpty(Mono.error(() -> new Exception("Not able to find a cyclist with the given id")))
                .flatMap(cyclist -> repository.deleteById(cyclist.getId()));
    }

}
