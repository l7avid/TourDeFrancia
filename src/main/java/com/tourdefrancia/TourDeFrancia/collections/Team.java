package com.tourdefrancia.TourDeFrancia.collections;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Document(collection = "team")
public class Team {

    @Id
    private String id;
    @NotBlank(message = "team name cannot be blank!")
    private String teamName;
    @NotBlank(message = "team code cannot be blank!")
    @Size(max = 3)
    private String teamCode;
    @NotBlank(message = "country cannot be blank!")
    private String country;
}
