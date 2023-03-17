package com.quiz.quiz;

public record QuizReqDTO(
        String name,
        String description,
        String instructions,
        Integer duration,
        Integer ownerId,
        Integer categoryId
) {
}
