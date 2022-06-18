package br.com.alexmdo.diningreviewapi.user.dto;

import lombok.Data;

@Data
public class FindByName {

    private Long id;
    private String city;
    private String state;
    private String zipCode;
    private boolean interestedInPeanutAllergies;
    private boolean interestedInEggAllergies;
    private boolean interestedInDairyAllergies;

}
