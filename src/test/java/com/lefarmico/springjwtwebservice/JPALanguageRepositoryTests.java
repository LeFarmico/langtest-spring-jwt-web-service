package com.lefarmico.springjwtwebservice;

import com.lefarmico.springjwtwebservice.entity.Category;
import com.lefarmico.springjwtwebservice.entity.Language;
import com.lefarmico.springjwtwebservice.repository.LanguageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class JPALanguageRepositoryTests {

    @Autowired
    LanguageRepository languageRepository;

    private Language testLanguage;

    @BeforeEach
    void setUp() {
        testLanguage = Language.builder()
                .languageName("Английский")
                .build();
    }

    @DisplayName("JUnit test for save language operation")
    @Test
    void givenLanguageObject_whenSave_thenReturn_Language() {
        Language language = Language.builder()
                .languageName("Испанский")
                .build();
        Language savedLanguage = languageRepository.save(language);
        assertThat(savedLanguage).isNotNull();
        assertThat(savedLanguage.getId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for get all language operation")
    @Test
    void givenLanguageList_whenFindAll_thenLanguageList() {
        Language language = Language.builder()
                .languageName("Испанский")
                .build();

        List<Language> languageList = languageRepository.findAll();

        languageRepository.save(testLanguage);
        languageRepository.save(language);

        List<Language> newLanguageList = languageRepository.findAll();

        assertThat(newLanguageList).isNotNull();
        assertThat(newLanguageList.size()).isEqualTo(languageList.size() + 2);
    }

    @DisplayName("JUnit test for get language by id operation")
    @Test
    void givenLanguageObject_whenFindById_thenReturnLanguageObject() {

        languageRepository.save(testLanguage);
        Language languageDB = languageRepository.findById(testLanguage.getId()).get();

        assertThat(languageDB).isNotNull();
    }

    @DisplayName("JUnit test for update language operation")
    @Test
    void givenCategoryObject_whenUpdateCategory_thenReturnUpdatedCategory() {

        languageRepository.save(testLanguage);
        Language categoryDB = languageRepository.findById(testLanguage.getId()).get();
        categoryDB.setLanguageName("Французкий");

        Language updatedLanguage = languageRepository.save(categoryDB);

        assertThat(updatedLanguage.getLanguageName()).isEqualTo("Французкий");
    }

    @DisplayName("JUnit test for delete language operation")
    @Test
    void givenLanguageObject_whenDelete_thenRemoveLanguage() {

        languageRepository.save(testLanguage);

        languageRepository.deleteById(testLanguage.getId());
        Optional<Language> languageOptional = languageRepository.findById(testLanguage.getId());

        assertThat(languageOptional).isEmpty();
    }
}
