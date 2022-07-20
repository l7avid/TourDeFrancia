package com.tourdefrancia.TourDeFrancia.routes.cyclist;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.usecases.cyclist.GetCyclistByCode;
import com.tourdefrancia.TourDeFrancia.usecases.cyclist.GetCyclistsByTeamCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetCyclistsByTeamCodeRoute {

    @Bean
    public RouterFunction<ServerResponse> getByTeamCode(GetCyclistsByTeamCode getCyclistsByTeamCode){
        return route(GET("/getbyteamcode/{teamCode}"),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(getCyclistsByTeamCode.getCyclistsByTeamCode(request.pathVariable("teamCode")),
                                CyclistDto.class))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }
}
