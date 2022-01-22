package com.inzynieria.wisielec.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class User {

    @Id
    @Column
    private String username;
    @Column
    private String password;

    @Transient
    private List<Character> guesses;
    @Transient
    public int lives;
    @Transient
    private boolean won;

    public User() {
        this.guesses = new ArrayList<>();
        this.won = false;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.guesses = new ArrayList<>();
        this.won = false;
    }

    public void addGuess(char guess){
        this.guesses.add(guess);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", guesses=" + guesses +
                ", lives=" + lives +
                ", won=" + won +
                '}';
    }
}
