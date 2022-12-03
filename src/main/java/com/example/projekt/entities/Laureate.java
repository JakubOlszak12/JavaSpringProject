package com.example.projekt.entities;

import lombok.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@ToString
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

    @OneToMany(mappedBy = "laureate")
    private List<NobelPrize> prizes;


    public Laureate(String givenName, String familyName, String gender, Date birthDate, String country, String wikipediaAddress, List<NobelPrize> prizes) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.country = country;
        this.wikipediaAddress = wikipediaAddress;
        this.prizes = prizes;
    }
}
