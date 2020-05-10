package com.wildadventures.msadventures.web.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AdventureImageDto {
    private String label;
    private String url;
    private int width;
    private int height;
}
