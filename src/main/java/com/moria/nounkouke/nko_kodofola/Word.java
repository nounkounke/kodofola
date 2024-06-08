package com.moria.nounkouke.nko_kodofola;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.Set;

@Document("words")
@Builder
@Data
public class Word {

    @Id
    @MongoId
    private String id;

    @Indexed
    private String parentSku;

    @Indexed(unique = true)
    private String sku;

    @Version
    private Integer version;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    @CreatedBy
    private String createdBySku;

    private Set<Translation> translations;

    private Set<Example> examples;
}
