package com.moria.nounkouke.nko_kodofola;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("introduction")
@RequiredArgsConstructor
public class IntroductionController {
    private IntroductionRepository introductionRepository;

    public void add(){
        var englishTranslation = Translation.builder()
                .language(Language.ENGLISH)
                .text("And God Said let there be light")
                .build();

        var frenchTranslation = Translation.builder()
                .text("Et Dieu dit, que la lumière soit")
                .language(Language.FRENCH)
                .build();

        var spanishTranslation = Translation.builder()
                .text("Y Dios dijo: 'Hágase la luz'.")
                .language(Language.FRENCH)
                .build();

        var nkoTranslation = Translation.builder()
                .text("ߊ߬ ߝߌߟߊ߲߫ ߞߊ߬ ߣߌ߲߬ ߞߍ߫ ߣߌ߲߬ ߖߊ߬ ߟߊ߫")
                .language(Language.NKO)
                .build();

        var hindiTranslation = Translation.builder()
                .text("और भगवान ने कहा, प्रकाश हो।")
                .language(Language.HINDI)
                .build();

        var intro = Introduction.builder()
                .translations(Arrays.asList(englishTranslation,frenchTranslation,
                        spanishTranslation,hindiTranslation,nkoTranslation))
                .build();

        introductionRepository.save(intro);
    }
}
