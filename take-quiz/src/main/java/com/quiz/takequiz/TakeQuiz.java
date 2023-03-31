package com.quiz.takequiz;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakeQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String quizName;    // VODI RACUNA AKO SE PROMJENI NAZIV KVIZA!
    private Integer quizId;
    private Integer quizOwnerId;
    // private double quizPoints; // ukupan broj poena koliko je moguce ostvariti na kvizu

    private Double result;  // points, total score
    private Integer durationOfSolving;

    @JsonFormat(pattern = "dd.MM.yyyy / HH:mm:ss", timezone = "Europe/Belgrade")
    private LocalDateTime dateOfSolving;

    private Integer solverId;

    // private boolean graded; // kad vlasnik oznaci da je kviz ocjenjene (pregleda sve odogovre na dopisivanje)
    // mozda umjesto graded ubaciti neki status rezultata
}
