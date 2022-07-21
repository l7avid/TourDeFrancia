package com.tourdefrancia.TourDeFrancia.routes.team;

import com.tourdefrancia.TourDeFrancia.Dto.TeamDto;
import com.tourdefrancia.TourDeFrancia.usecases.team.NewTeam;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class NewTeamRoute {

    @Bean
    public RouterFunction<ServerResponse> createTeam(NewTeam newTeam){
        return route(POST("/create/team").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TeamDto.class)
                        .flatMap(teamDto -> newTeam.newTeam(teamDto))
                        .flatMap(teamDto -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(teamDto)));
    }
}
