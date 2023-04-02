package com.quiz.takequiz.dto;

import com.quiz.takequiz.ResultStatus;

public record UpdateSolvedQuiz(Double result, Integer durationOfSolving, ResultStatus status) {
}
