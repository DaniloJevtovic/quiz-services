package com.quiz.quiz.dto;

import com.quiz.quiz.model.QuestionType;

public record QuestionReqDTO(String question, QuestionType type, Integer quizId) {
}
