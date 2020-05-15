package com.wildadventures.mscomments.controllers.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class CommentDto {
    private CommentIdDTO id;

    private WriterDTO writer;
    private LocalDateTime date;
    @Size(min = 1)
    private String content;
}