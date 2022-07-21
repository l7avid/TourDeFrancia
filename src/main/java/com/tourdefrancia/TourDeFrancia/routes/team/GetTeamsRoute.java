package com.tourdefrancia.TourDeFrancia.routes.team;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.Dto.TeamDto;
import com.tourdefrancia.TourDeFrancia.usecases.cyclist.GetAllCyclists;
import com.tourdefrancia.TourDeFrancia.usecases.team.GetTeams;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetTeamsRoute {

    @Bean
    public RouterFunction<ServerResponse> getAllTeams(GetTeams getTeams){
        return route(GET("/get/teams"), request -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(getTeams.getAllTeams(), TeamDto.class)));
    }
}
