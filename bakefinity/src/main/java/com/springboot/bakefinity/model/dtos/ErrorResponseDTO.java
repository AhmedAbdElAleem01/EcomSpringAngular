package com.springboot.bakefinity.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDTO {
    private LocalDateTime timestamp;
    private String message;
    private String path;
    private int status;
    private String error;
}
