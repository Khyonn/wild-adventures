package com.wildadventures.mscomments.controllers.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private CommentIdDTO id;

    private WriterDTO writer;
    private LocalDateTime date;
    private String content;
}