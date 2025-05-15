package com.nanoapp.nano.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TodoRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title must be less than 100 characters")
        String title,
        
        @Size(max = 500, message = "Description must be less than 500 characters")
        String description
) {}
