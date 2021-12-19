package com.bng.ddaja.wordQuestions.repository;

import com.bng.ddaja.common.domain.Word;
import com.bng.ddaja.common.domain.WordQuestion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WordQuestionRepository extends JpaRepository< WordQuestion, Long >, JpaSpecificationExecutor< WordQuestion >{

    public WordQuestion findById( long id ); 
    public List<WordQuestion> findByWord( Word word );

}
