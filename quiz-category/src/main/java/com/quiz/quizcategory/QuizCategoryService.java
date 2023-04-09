package com.quiz.quizcategory;

import com.quiz.clients.quiz.QuizClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuizCategoryService {

    private final QuizCategoryRepository quizCategoryRepository;
    private final QuizClient quizClient;

    public List<QuizCategory> getAll() {
        return quizCategoryRepository.findAll();
    }

    public QuizCategory getCategoryById(Integer id) {
        return quizCategoryRepository.findById(id).orElse(null);
    }

    public QuizCategory saveCategory(QuizCategory quizCategory) {
        return quizCategoryRepository.save(quizCategory);
    }

    public QuizCategory updateCategory(QuizCategory quizCategory) {
        QuizCategory category = getCategoryById(quizCategory.getId());
        category.setName(quizCategory.getName());
        category.setDescription(quizCategory.getDescription());

        return quizCategoryRepository.save(category);
    }

    public String deleteCategory(Integer id) {
        if (quizClient.checkQuizzesInCategory(id)) return "Nije moguce obrisati kategoriju, postoje kvizovi u njoj!";

        quizCategoryRepository.deleteById(id);
        return "Kategorija uspjesno obrisana";
    }

}
