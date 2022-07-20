package com.tourdefrancia.TourDeFrancia.routes.cyclist;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.mappers.CyclistMapper;
import com.tourdefrancia.TourDeFrancia.repository.ICyclistRepo;
import com.tourdefrancia.TourDeFrancia.usecases.cyclist.GetCyclistByCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetCyclistByCodeRoute {

    private final ICyclistRepo repository;
    private final CyclistMapper mapper;


    public GetCyclistByCodeRoute(ICyclistRepo repository, CyclistMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Bean
    public RouterFunction<ServerResponse> getByCode(GetCyclistByCode getCyclistByCode){
        return route(GET("/get/{code}"),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(getCyclistByCode.getCyclistByCode(request.pathVariable("code")),
                                CyclistDto.class))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }
}
