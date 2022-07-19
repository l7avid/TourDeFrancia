package com.tourdefrancia.TourDeFrancia.collections;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document(collection = "team")
public class Team {

    @Id
    private String id;
    @NotBlank(message = "")
}
