package com.quiz.selectanswer;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class SelectAnswer {

    @Id
    private String id;
    private String questionId;
    private String choiceId;
    private Boolean correct;
    private Double points;
    private Integer quizResultId;
}
