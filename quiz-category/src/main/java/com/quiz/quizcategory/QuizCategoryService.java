package com.quiz.quizcategory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuizCategoryService {

    private final QuizCategoryRepository quizCategoryRepository;

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

    public void deleteCategoru(Integer id) {
        // provjeriti da li postoje kvizovi u kategoriji, provjeriti da li su ti kvizovi radjeni
        quizCategoryRepository.deleteById(id);
    }

}
