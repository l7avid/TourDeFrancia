package com.tourdefrancia.TourDeFrancia.routes.cyclist;

import com.tourdefrancia.TourDeFrancia.usecases.cyclist.DeleteCyclist;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteCyclistRoute {

    @Bean
    public RouterFunction<ServerResponse> deleteSingleCyclist(DeleteCyclist deleteCyclist){
        return route(DELETE("/delete/cyclist/{id}"),
                request -> deleteCyclist.deleteCyclis(request.pathVariable("id"))
                        .flatMap((unused) -> ServerResponse.accepted().build())
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }
}
