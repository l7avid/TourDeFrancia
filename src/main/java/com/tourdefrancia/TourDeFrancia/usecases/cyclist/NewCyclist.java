package com.tourdefrancia.TourDeFrancia.usecases.cyclist;

import com.tourdefrancia.TourDeFrancia.Dto.CyclistDto;
import com.tourdefrancia.TourDeFrancia.mappers.CyclistMapper;
import com.tourdefrancia.TourDeFrancia.repository.ICyclistRepo;
import com.tourdefrancia.TourDeFrancia.repository.ITeamRepo;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Validated
public class NewCyclist {

    private final ICyclistRepo repository;
    private final ITeamRepo teamRepository;
    private final CyclistMapper mapper;

    public NewCyclist(ICyclistRepo repository, ITeamRepo teamRepository, CyclistMapper mapper) {
        this.repository = repository;
        this.teamRepository = teamRepository;
        this.mapper = mapper;
    }

    public Boolean validateTeamCode(CyclistDto cyclistDto){
        String teamCodeIncoming = cyclistDto.getTeamCode();
        Mono<Boolean> teamCode = teamRepository.existsByTeamCode(teamCodeIncoming);
        AtomicReference<Boolean> isAValidTeamCode = new AtomicReference<>(false);
        teamCode.zipWith(Mono.empty()).map(tuple -> {
            System.out.println("Team code exists?: " + tuple.getT1());
            if(Boolean.TRUE.equals(tuple.getT1())){
                isAValidTeamCode.set(true);
            }
            return isAValidTeamCode.get();
        }).subscribe();
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return isAValidTeamCode.get();
    }

    public Boolean validateTeamSize(CyclistDto cyclistDto){
        System.out.println(validateTeamCode(cyclistDto));
        Mono<Long> teamMembers = repository.findByTeamCode(cyclistDto.getTeamCode()).count();
        AtomicReference<Boolean> isFull = new AtomicReference<>(false);
        teamMembers.zipWith(Mono.just(8l)).map(tuple -> {
//            System.out.println("Limit members: ");
//            System.out.println(tuple.getT2());
            Long newTuple = tuple.getT1() + 1;
//            System.out.println("Team members with the last one added: " + newTuple);
            if(newTuple > tuple.getT2()){
                isFull.set(true);
            }
            return isFull.get();
        }).subscribe();
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return isFull.get();
    }

    public Mono<CyclistDto> validateCyclistCode(CyclistDto cyclistDto){
        String cyclistCodeIncoming = cyclistDto.getCyclistCode();
        if(!validateTeamSize(cyclistDto) && (validateTeamCode(cyclistDto))){
            return repository.existsByCyclistCode(cyclistCodeIncoming).flatMap(aBoolean -> {
                if(Boolean.TRUE.equals(aBoolean)){
                    return Mono.error(() -> new Exception("The given cyclist code is already in use"));
                }
                return repository.save(mapper.toCyclist(cyclistDto)).map(cyclist -> mapper.toCyclistDto(cyclist));
            });
        }else {
            return Mono.error(() -> new Exception("There was an error while creating a cyclist!"));
        }
    }

    public Mono<CyclistDto> newCyclist(@Valid CyclistDto cyclistDto){
            return validateCyclistCode(cyclistDto);
//                    .flatMap(cyclistDto1 -> repository.save(mapper.toCyclist(cyclistDto)).map(cyclist -> mapper.toCyclistDto(cyclist)));
    }
}
