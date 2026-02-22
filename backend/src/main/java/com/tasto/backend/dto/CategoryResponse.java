package com.tasto.backend.dto;

import com.tasto.backend.entity.CategoryModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryResponse {
    private boolean success;
    private String message;
    private List<CategoryModel> category;
}
