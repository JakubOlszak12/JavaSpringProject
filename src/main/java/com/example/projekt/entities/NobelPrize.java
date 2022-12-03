package com.example.projekt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name="laureate_id")
    private Laureate laureate;
    private String motivation;

    public NobelPrize(int awardYear, int prize, int prizeAdjusted, Laureate laureate, String motivation) {
        this.awardYear = awardYear;
        this.prize = prize;
        this.prizeAdjusted = prizeAdjusted;
        this.laureate = laureate;
        this.motivation = motivation;
    }
}
