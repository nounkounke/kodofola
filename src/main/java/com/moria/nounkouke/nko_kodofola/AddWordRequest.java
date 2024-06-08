package com.moria.nounkouke.nko_kodofola;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class AddWordRequest {
    private String parentSku;
    private String sku;
    private Integer version;
    private Date createdDate;
    private Date lastModifiedDate;
    private String createdBySku;
    private Set<Translation> translations;
    private Set<Transliteration> transliterations;
    private Set<Example> examples;
}
