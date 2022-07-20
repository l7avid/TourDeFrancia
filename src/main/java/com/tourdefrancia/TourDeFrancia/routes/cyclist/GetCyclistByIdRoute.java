package com.tourdefrancia.TourDeFrancia.routes.cyclist;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.usecases.cyclist.GetCyclistById;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetCyclistByIdRoute {

    @Bean
    public RouterFunction<ServerResponse> getById(GetCyclistById getCyclistById){
        return route(GET("getbyid/{id}"),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(getCyclistById.getCyclistById(request.pathVariable("id")),
                                CyclistDto.class))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }
}
