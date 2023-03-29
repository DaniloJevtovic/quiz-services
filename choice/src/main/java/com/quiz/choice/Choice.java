package com.quiz.choice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Choice {

    @Id
    private String id;
    private String choice;
    @JsonIgnore
    private Boolean correct;
    private String questionId;
    private Integer quizId;
}
