package com.tasto.backend.service;

import com.tasto.backend.dto.SearchResponse;

public interface SearchService {
    public SearchResponse foodSearch(String q);
}
