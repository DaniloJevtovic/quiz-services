package com.quiz.useranswer;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Answer {

    @Id
    private String id;
    private String answer; // ovaj atribut moze da cuva odgovor koji se dopisuje ili chose odgovor
    private String questionId;
    private Boolean correct;
    private AnswerType type;
    private Double points;
    private Integer quizResultId;
    private Integer solverId;
}
