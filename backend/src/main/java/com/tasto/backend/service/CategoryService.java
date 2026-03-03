package com.tasto.backend.service;

import com.tasto.backend.dto.CategoryResponse;
import com.tasto.backend.entity.CategoryModel;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(List<CategoryModel>categoryRequest);
}
