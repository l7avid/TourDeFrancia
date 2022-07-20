package com.tourdefrancia.TourDeFrancia.routes.cyclist;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.usecases.cyclist.UpdateCyclist;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UpdateCyclistRoute {

    @Bean
    public RouterFunction<ServerResponse> updateSingleCyclist(UpdateCyclist updateCyclist){
        return route(PUT("/update/cyclist").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CyclistDto.class)
                        .flatMap(cyclistDto -> updateCyclist.updateCyclist(cyclistDto))
                        .flatMap(cyclistDto -> ServerResponse.status(HttpStatus.ACCEPTED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(cyclistDto)));
    }
}
