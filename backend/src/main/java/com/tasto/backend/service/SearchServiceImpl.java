package com.tasto.backend.service;

import com.tasto.backend.dto.SearchResponse;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService{
    @Override
    public SearchResponse foodSearch(String q) {
      return new SearchResponse();
    }
}
