package com.tourdefrancia.TourDeFrancia.Dto;

import com.tourdefrancia.TourDeFrancia.collections.Cyclist;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class TeamDto {

    private String id;
    @NotBlank(message = "team name cannot be blank!")
    private String teamName;
    @NotBlank(message = "team code cannot be blank!")
    @Size(max = 3)
    private String teamCode;
    @NotBlank(message = "country cannot be blank!")
    private String country;
    @Size(max = 8)
    private List<Cyclist> cyclistsList;
}
