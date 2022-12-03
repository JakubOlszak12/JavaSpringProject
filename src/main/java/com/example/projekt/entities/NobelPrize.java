package com.example.projekt.entities;

import lombok.*;

import javax.persistence.*;

@ToString
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
    private String category;
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name="laureate_id")
    private Laureate laureate;
    @Column(columnDefinition = "TEXT")
    private String motivation;

    public NobelPrize(int awardYear, int prize, int prizeAdjusted, String category, Laureate laureate, String motivation) {
        this.awardYear = awardYear;
        this.prize = prize;
        this.prizeAdjusted = prizeAdjusted;
        this.category = category;
        this.laureate = laureate;
        this.motivation = motivation;
    }
}
