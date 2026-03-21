package com.tasto.backend.controller;

import com.tasto.backend.dto.SearchResponse;
import com.tasto.backend.entity.FoodModel;
import com.tasto.backend.service.FoodService;
import com.tasto.backend.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;
    public SearchController(SearchService searchService)
    {
        this.searchService=searchService;
    }
    @GetMapping("/")
    public ResponseEntity<SearchResponse> searchFood(@RequestParam String q)
    {
        SearchResponse response = searchService.foodSearch(q);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
