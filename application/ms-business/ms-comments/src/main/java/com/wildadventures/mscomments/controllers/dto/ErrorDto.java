package com.wildadventures.mscomments.controllers.dto;

import lombok.Data;

@Data
public class ErrorDto {
    private String type;
    private String message;

    public ErrorDto() {
    }

    public ErrorDto(String message) {
        this.message = message;
    }

    public ErrorDto(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
