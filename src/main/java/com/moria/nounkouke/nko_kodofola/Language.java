package com.moria.nounkouke.nko_kodofola;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Language {
    NKO("nko"),ARABIC("arabic"),FRENCH("french"),
    ENGLISH("english"),SWAHILI("swahili"),AMHARIC("amharic"),
    HINDI("hindi"),LATIN("latin");

    private final String value;
}
