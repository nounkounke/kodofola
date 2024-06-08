package com.moria.nounkouke.nko_kodofola;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Slf4j
public class WordService {
    private final WordRepository wordRepository;

    public Word addWord(AddWordRequest request){
        try {

            var word = Word.builder()
                    .parentSku(request.getParentSku())
                    .translations(request.getTranslations())
                    .examples(request.getExamples())
                    .build();



            return wordRepository.save(word);
        }catch (DuplicateKeyException e){
            throw new RuntimeException("non unique index found during persistence",e);
        }
    }
}
