package com.example.projekt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Laureate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long laureateId;
    private String givenName;
    private String familyName;
    private String gender;
    private Date birthDate;
    private String country;
    private String wikipediaAddress;

    public Laureate(String givenName, String familyName, String gender, Date birthDate, String country, String wikipediaAddress) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.country = country;
        this.wikipediaAddress = wikipediaAddress;

    }
}
