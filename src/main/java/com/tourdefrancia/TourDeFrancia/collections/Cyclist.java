package com.tourdefrancia.TourDeFrancia.collections;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Document(collection = "cyclist")
public class Cyclist {

    @Id
    private String id;
    @NotBlank(message = "name cannot be blank!")
    private String cyclistName;
    @NotBlank(message = "cyclist code cannot be blank!")
    @Size(max = 3)
    private String cyclistCode;
    private Team team;
    @NotBlank(message = "country cannot be blank!")
    private String country;


}
