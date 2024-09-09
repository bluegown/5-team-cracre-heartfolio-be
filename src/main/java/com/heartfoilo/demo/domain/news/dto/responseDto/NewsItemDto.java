package com.heartfoilo.demo.domain.news.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsItemDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String originallink;
    private String link;
    private String description;
    private String pubDate;
}