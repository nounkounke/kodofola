package com.moria.nounkouke.nko_kodofola;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("nko")
@RequiredArgsConstructor
@Slf4j
public class WordController {
    private final WordService wordService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<AddWordResponse> add(@RequestBody @Valid AddWordRequest request){
        var result = wordService.addWord(request);
        var response = modelMapper.map(result, AddWordResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
