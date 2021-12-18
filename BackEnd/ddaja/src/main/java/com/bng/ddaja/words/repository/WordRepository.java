package com.bng.ddaja.words.repository;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.domain.Word;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WordRepository extends JpaRepository< Word, Long >, JpaSpecificationExecutor< Word >{
    
    public Word findById( long wid );

    public Word findByTitle( String title );

    public List<Word> findByLicense( License license );
}