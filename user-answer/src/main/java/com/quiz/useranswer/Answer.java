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
    // private String question; // pazi i razmisli - ako se promjeni pitanje ovdje se ne mjenja, dovoljno je questionId
    private Integer questionId;
    private Boolean correct;
    private Integer quizResultId;
    private Integer solverId;

    // mozda ubaciti i tip odgovora - dopisivanje ili choice
    // ako je choice odmah po zavrsetku kviza setovati correct atribut
    // a ako je na dopisivanje - sacekati da vlasnik kviza oznaci odgovor kao tacan ili ne

    // na frontendu - choice - select odgovora - odgovor se cuva, deselect - odgovor se brise
    // odgovor koji se pise - na kraju se cuva
}
