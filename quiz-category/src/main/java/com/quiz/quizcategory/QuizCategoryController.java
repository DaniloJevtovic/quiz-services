package com.quiz.quizcategory;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@AllArgsConstructor
public class QuizCategoryController {

    private final QuizCategoryService quizCategoryService;

    @GetMapping
    public List<QuizCategory> getAllCategories() {
        return quizCategoryService.getAll();
    }

    @GetMapping("/{id}")
    public QuizCategory getCategoryById(@PathVariable Integer id) {
        return quizCategoryService.getCategoryById(id);
    }

    @PostMapping
    public QuizCategory createCategory(@RequestBody QuizCategory quizCategory) {
        return quizCategoryService.saveCategory(quizCategory);
    }

    @PutMapping
    public QuizCategory updateCategory(@RequestBody QuizCategory quizCategory) {
        return quizCategoryService.updateCategory(quizCategory);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        return quizCategoryService.deleteCategory(id);
    }
}
