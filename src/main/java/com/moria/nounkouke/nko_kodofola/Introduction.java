package com.moria.nounkouke.nko_kodofola;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@Document("introduction")
public class Introduction {
    private List<Translation> translations;
}
