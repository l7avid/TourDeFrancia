package com.tourdefrancia.TourDeFrancia.routes.team;

import com.tourdefrancia.TourDeFrancia.usecases.team.DeleteTeam;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteTeamRoute {

    @Bean
    public RouterFunction<ServerResponse> deleteSingleTeam(DeleteTeam deleteTeam){
        return route(DELETE("/delete/team/{id}"),
                request -> deleteTeam.deleteTeam(request.pathVariable("id"))
                        .flatMap((unused) -> ServerResponse.accepted().build())
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }
}
