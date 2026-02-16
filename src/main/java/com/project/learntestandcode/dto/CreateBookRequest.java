package com.project.learntestandcode.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class CreateBookRequest {
    @NotBlank(message = "Title can't be a blank'")
    @Size(max = 100, message = "Title can't exceed 100 characters")
    String title;
}
