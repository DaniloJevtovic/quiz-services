package com.quiz.quiz;

import com.quiz.clients.notification.NotificationDTO;
import com.quiz.rabbitmq.RabbitMQMessageProducer;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public Page<Quiz> getAll(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    public Quiz getQuizById(Integer id) {
        return quizRepository.findById(id).orElse(null);
    }

    public List<Quiz> getQuizziesForUser(Integer userId) {
        return quizRepository.findByOwnerId(userId);
    }

    public Page<Quiz> getQuizziesForCategoryAndStatus(Integer categoryId, QuizStatus status, Pageable pageable) {
        return quizRepository.findByCategoryIdAndStatus(categoryId, status, pageable);
    }

    public Quiz createQuiz(QuizReqDTO quizReqDTO) {
        Quiz quiz = new Quiz();
        quiz.setName(quizReqDTO.name());
        quiz.setDescription(quizReqDTO.description());
        quiz.setInstructions(quizReqDTO.instructions());
        quiz.setDuration(quizReqDTO.duration());
        quiz.setStatus(QuizStatus.WAITING_APPROVAL);
        quiz.setCreated(LocalDateTime.now());
        quiz.setLastUpdate(LocalDateTime.now());
        quiz.setOwnerId(quizReqDTO.ownerId());
        quiz.setCategoryId(quizReqDTO.categoryId());
        quiz.setNumOfSolves(0);

        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(Integer quizId, QuizReqDTO quizReqDTO) {
        Quiz quiz = getQuizById(quizId);

        // provjera da li je kviz vec rjesavan (moze i nesto drugo - zahtjev moderatoro za izmjenu...)
        if (quiz.getNumOfSolves() > 0)
            return quiz;

        quiz.setName(quizReqDTO.name());
        quiz.setDescription(quizReqDTO.description());
        quiz.setInstructions(quizReqDTO.instructions());
        quiz.setLastUpdate(LocalDateTime.now());
        quiz.setDuration(quizReqDTO.duration());
        quiz.setCategoryId(quizReqDTO.categoryId());

        return quizRepository.save(quiz);
    }

    public Integer increaseNumOfSolves(Integer quizId) {
        Quiz quiz = getQuizById(quizId);

        if (quiz == null) return -1;

        int numOfSolves = quiz.getNumOfSolves();
        quiz.setNumOfSolves(++numOfSolves);
        quizRepository.save(quiz);

        return numOfSolves;
    }

    // user (active, inactive)
    public Quiz changeQuizStatus(Integer quizId, QuizStatus quizStatus) {
        Quiz quiz = getQuizById(quizId);
        quiz.setStatus(quizStatus);
        quizRepository.save(quiz);
        return quiz;
    }

    // moderator
    public Quiz approveQuiz(Integer quizId) {
        Quiz quiz = getQuizById(quizId);
        quiz.setStatus(QuizStatus.ACTIVE);

        rabbitMQMessageProducer.publish(
                new NotificationDTO("Vas kviz: " + quiz.getName() + " je odobren!", quiz.getOwnerId()),
                "notification.exchange",
                "notification.routing-key"
        );

        return quizRepository.save(quiz);
    }

    public boolean checkQuizSolved(Integer quizId) {
        Quiz quiz = getQuizById(quizId);
        return quiz.getNumOfSolves() == 0;
    }

    public void deleteQuiz(Integer id) {
        Quiz quiz = getQuizById(id);

        // provjeriti da li je kviz radjen, tj da li postoje rezulatati za njega
        if (quiz.getNumOfSolves() > 0) {
            quiz.setStatus(QuizStatus.DELETED);
            quizRepository.save(quiz);
            return;
        }

        // poziv servisa za brisanje pitanja
        rabbitMQMessageProducer.publish(id, "del-quiz.exchange", "del-quiz.routing-key");
        quizRepository.deleteById(id);
    }

    public Double deleteAllQuizesForCategory(Integer categoryId) {
        return quizRepository.deleteByCategoryId(categoryId);
    }
}
