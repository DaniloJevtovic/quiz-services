package com.quiz.quiz;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String instructions;
    private Integer duration;

    @Enumerated(EnumType.STRING)
    private QuizStatus status;

    private LocalDateTime created;
    private LocalDateTime lastUpdate;
    private Integer ownerId;
    private Integer categoryId;
    private int numOfSolves;

    // private/public quiz - enum
    // private QuizType type;

    // private Double rating;   // rating / num of solves
    // mozda poseban servis za rating (1-5 zvjezdica bez komentara (jer bi mogli da se pisu pitanja)
}
