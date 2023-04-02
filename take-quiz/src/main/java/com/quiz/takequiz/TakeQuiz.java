package com.quiz.takequiz;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private ResultStatus resultStatus;

    private Integer solverId;
}
