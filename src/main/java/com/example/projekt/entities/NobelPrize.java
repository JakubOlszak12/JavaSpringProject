package com.example.projekt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NobelPrize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long prizeId;
    private int awardYear;
    private int prize;
    private int prizeAdjusted;
    private long laureateId;
    private String motivation;

    public NobelPrize(int awardYear, int prize, int prizeAdjusted, long laureateId, String motivation) {
        this.awardYear = awardYear;
        this.prize = prize;
        this.prizeAdjusted = prizeAdjusted;
        this.laureateId = laureateId;
        this.motivation = motivation;
    }
}
