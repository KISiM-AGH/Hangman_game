package com.inzynieria.wisielec.controller;

import com.inzynieria.wisielec.entity.Phrase;
import com.inzynieria.wisielec.entity.User;
import com.inzynieria.wisielec.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private PhraseRepository phraseRepository;

    @PostMapping("/phrase/{phrase}")
    public int setPhrase(@PathVariable("phrase") String phrase){
        Phrase temp = phraseRepository.findByPhrase(phrase);

        if(temp != null)
            return temp.getId();

        Phrase saved = new Phrase(phrase);

        phraseRepository.save(saved);

        return phraseRepository.findByPhrase(phrase).getId();
    }

    @GetMapping("/phrase/{phraseId}")
    public Phrase getPhrase(@PathVariable ("phraseId") int phraseId){
        Optional<Phrase> temp = phraseRepository.findById(phraseId);

        if(temp.isPresent())
            return temp.get();

        return null;
    }

    @GetMapping("/phrase")
    public Phrase getRandomPhrase(){
        Random random = new Random();

        Optional<Phrase> temp = phraseRepository.findById(random.nextInt((int)phraseRepository.count()));

        if(temp.isPresent())
            return temp.get();

        return null;
    }

    @GetMapping("/guess/{phraseId}/{guess}")
    public User guessChar(@RequestBody User user, @PathVariable ("guess") char guess, @PathVariable ("phraseId") int phraseId){
        if(user.getGuesses().contains(guess)){
            user.lives --;
            return user;
        }
        Phrase phrase = phraseRepository.findById(phraseId).get();

        for(char c : phrase.getPhrase().toCharArray()){
            if(c==guess){
                user.addGuess(guess);

                for(char c2 : phrase.getPhrase().toCharArray()){
                    if(!user.getGuesses().contains(c2)){
                        return user;
                    }
                }
                user.setWon(true);
                return user;
            }
        }
        user.addGuess(guess);
        user.lives--;
        return user;
    }

    @GetMapping("/guessPhrase/{phraseId}/{guess}")
    public User guessPhrase(@RequestBody User user, @PathVariable("guess") String guess, @PathVariable ("phraseId") int phraseId){
        Phrase phrase = phraseRepository.findById(phraseId).get();

        if(phrase.getPhrase().equals(guess)){
            user.setWon(true);
            return user;
        }

        user.lives--;
        return user;
    }

    @GetMapping("/game")
    public User[] startGame(@RequestBody User[] users, @RequestParam ("lives") int lives){
        for(User c: users)
            c.lives = lives;

        return users;

    }
}
