package com.inzynieria.wisielec.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Phrase {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String phrase;

    public Phrase() {
    }

    public Phrase(String phrase) {
        this.phrase = phrase;
    }
}
