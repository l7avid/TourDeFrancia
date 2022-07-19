package com.tourdefrancia.TourDeFrancia.Dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CyclistDto {

    private String id;
    @NotBlank(message = "name cannot be blank!")
    private String cyclistName;
    @NotBlank(message = "cyclist code cannot be blank!")
    @Size(max = 3)
    private String cyclistCode;
    @NotBlank(message = "country cannot be blank!")
    private String country;
}
