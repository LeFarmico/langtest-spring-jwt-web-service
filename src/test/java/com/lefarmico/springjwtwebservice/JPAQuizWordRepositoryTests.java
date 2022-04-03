package com.lefarmico.springjwtwebservice;

import com.lefarmico.springjwtwebservice.entity.QuizWord;
import com.lefarmico.springjwtwebservice.repository.QuizWordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class JPAQuizWordRepositoryTests {

    @Autowired
    QuizWordRepository quizWordRepository;

    private QuizWord testQuizWord;

    @BeforeEach
    void setUp() {
        List<String> testWrongAnswers= new ArrayList();
        testWrongAnswers.add("Лиса");
        testWrongAnswers.add("Волк");
        testQuizWord = QuizWord.builder()
                .clientId("weoifwoe")
                .originalWord("Dog")
                .correctTranslation("Собака")
                .isAnswered(false)
                .wrongTranslations(testWrongAnswers)
                .build();
    }

    @DisplayName("JUnit test for save quizWord operation")
    @Test
    void givenQuizWordObject_whenSave_thenReturn_QuizWord() {
        List<String> wrongAnswers= new ArrayList();
        wrongAnswers.add("Яблоко");
        wrongAnswers.add("Груша");
        QuizWord quiz = QuizWord.builder()
                .clientId("weoifwopefi")
                .originalWord("Orange")
                .correctTranslation("Апельсин")
                .isAnswered(true)
                .wrongTranslations(wrongAnswers)
                .build();
        QuizWord savedQuizWord = quizWordRepository.save(quiz);
        assertThat(savedQuizWord).isNotNull();
        assertThat(savedQuizWord.getId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for get all quizWord operation")
    @Test
    void givenQuizWordList_whenFindAll_thenQuizWordList() {
        List<String> wrongAnswers= new ArrayList();
        wrongAnswers.add("Яблоко");
        wrongAnswers.add("Груша");
        QuizWord quiz = QuizWord.builder()
                .clientId("weoifwopefi")
                .originalWord("Orange")
                .correctTranslation("Апельсин")
                .isAnswered(true)
                .wrongTranslations(wrongAnswers)
                .build();

        List<QuizWord> quizWordList = quizWordRepository.findAll();

        quizWordRepository.save(quiz);
        quizWordRepository.save(testQuizWord);

        List<QuizWord> newQuizWordList = quizWordRepository.findAll();

        assertThat(newQuizWordList).isNotNull();
        assertThat(newQuizWordList.size()).isEqualTo(quizWordList.size() + 2);
    }

    @DisplayName("JUnit test for get quizWord by id operation")
    @Test
    void givenQuizWordObject_whenFindById_thenReturnQuizWordObject() {

        quizWordRepository.save(testQuizWord);
        QuizWord quizWordDB = quizWordRepository.findById(testQuizWord.getId()).get();

        assertThat(quizWordDB).isNotNull();
    }

    @DisplayName("JUnit test for update quizWord operation")
    @Test
    void givenQuizWordObject_whenUpdateQuiz_thenReturnUpdatedQuizWord() {

        quizWordRepository.save(testQuizWord);
        QuizWord quizWordDB = quizWordRepository.findById(testQuizWord.getId()).get();
        quizWordDB.setClientId("133r4");
        quizWordDB.setOriginalWord("Pool");
        quizWordDB.setCorrectTranslation("Бассейн");
        quizWordDB.setIsAnswered(true);

        List<String> updatedWringAnswers = new ArrayList<>();
        updatedWringAnswers.add("Машина");
        updatedWringAnswers.add("Вертолет");

        quizWordDB.setWrongTranslations(updatedWringAnswers);

        QuizWord updatedQuiz = quizWordRepository.save(quizWordDB);

        assertThat(updatedQuiz.getClientId()).isEqualTo("133r4");
        assertThat(updatedQuiz.getOriginalWord()).isEqualTo("Pool");
        assertThat(updatedQuiz.getCorrectTranslation()).isEqualTo("Бассейн");
        assertThat(updatedQuiz.getIsAnswered()).isEqualTo(true);
        assertThat(updatedQuiz.getWrongTranslations()).isEqualTo(updatedWringAnswers);
    }

    @DisplayName("JUnit test for delete quizWord operation")
    @Test
    void givenQuizWordObject_whenDelete_thenRemoveQuizWord() {

        quizWordRepository.save(testQuizWord);

        quizWordRepository.deleteById(testQuizWord.getId());
        Optional<QuizWord> quizWordOptional = quizWordRepository.findById(testQuizWord.getId());

        assertThat(quizWordOptional).isEmpty();
    }
}