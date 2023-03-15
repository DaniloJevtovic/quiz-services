package com.quiz.question;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Question {

    @Id
    private String id;
    private String question;
    private QuestionType type;
    private Integer quizId;
}
