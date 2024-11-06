package com.DataProcessing3.DP_BackEnd.domain.naverAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NaverSearchController {
    @Autowired
    private NaverSearchService naverSearchService;

    @GetMapping("/api/search")
    public Map<String, List<?>> search(@RequestParam String query) {
        Map<String, List<?>> results = new HashMap<>();
        results.put("news", naverSearchService.search(query, "news"));
        results.put("blog", naverSearchService.search(query, "blog"));
        results.put("cafe", naverSearchService.search(query, "cafearticle"));
        return results;
    }

}
