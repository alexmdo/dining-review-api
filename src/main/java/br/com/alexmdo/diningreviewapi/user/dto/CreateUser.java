package br.com.alexmdo.diningreviewapi.user.dto;

import lombok.Data;

@Data
public class CreateUser {

    private Long id;
    private String name;
    private String city;
    private String state;
    private String zipCode;
    private boolean interestedInPeanutAllergies;
    private boolean interestedInEggAllergies;
    private boolean interestedInDairyAllergies;

}
