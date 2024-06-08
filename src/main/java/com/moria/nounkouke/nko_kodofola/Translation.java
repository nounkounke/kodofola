package com.moria.nounkouke.nko_kodofola;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class Translation {
    private String text;
    private Language language;
    private String usage;
    private String context;
    private String history;
    private Set<Transliteration> transliterations;
    //TODO add field identifier for words with similar meaning in other languages
    //TODO add field identifier for phonetically  similar words meaning in other languages
    //TODO add phonetic variations in the same languages
}
