package com.tasto.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
    private boolean success;
    private String message;
    private List<FoodResult>result;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class FoodResult{
        private String name;
        private String image;
        private String category;
    }
}
