package com.quiz.quiz.dto;

public record QuizReqDTO(String name, String instructions, Integer duration, Integer ownerId, Integer categoryId) {
}
