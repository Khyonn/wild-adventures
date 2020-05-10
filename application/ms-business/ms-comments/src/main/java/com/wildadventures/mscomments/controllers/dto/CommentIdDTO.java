package com.wildadventures.mscomments.controllers.dto;

import lombok.Data;

@Data
public class CommentIdDTO {
    private Integer adventureId;
    private Integer commentId;
    private String userId;
}
