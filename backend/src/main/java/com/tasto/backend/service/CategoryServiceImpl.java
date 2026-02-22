package com.tasto.backend.service;

import com.tasto.backend.dto.CategoryResponse;
import com.tasto.backend.entity.CategoryModel;
import com.tasto.backend.exception.InvalidRequestException;
import com.tasto.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository)
    {
        this.categoryRepository=categoryRepository;
    }
    @Override
    public CategoryResponse addCategory(List<CategoryModel>categoryRequest)
    {
        if(categoryRequest.isEmpty())
        {
            throw new InvalidRequestException("Please provide the category");
        }

       for(CategoryModel category: categoryRequest) {
           if(category.getCount()<0)   category.setCount(0);
           categoryRepository.save(category);
       }
       return new CategoryResponse(true,"Category Added Successfully", categoryRequest);
    }
}
