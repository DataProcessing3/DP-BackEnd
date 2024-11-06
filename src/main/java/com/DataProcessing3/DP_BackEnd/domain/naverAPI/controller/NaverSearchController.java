package com.DataProcessing3.DP_BackEnd.domain.naverAPI.controller;

import com.DataProcessing3.DP_BackEnd.domain.naverAPI.service.NaverSearchService;
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

    //검색 결과 출력
    @GetMapping("/api/search")
    public Map<String, List<?>> search(@RequestParam("query") String query) {
        Map<String, List<?>> results = new HashMap<>();
        //뉴스 파트
        results.put("news", naverSearchService.search(query, "news"));
        //블로그 파트
        results.put("blog", naverSearchService.search(query, "blog"));
        //카페 파트
        results.put("cafe", naverSearchService.search(query, "cafearticle"));
        return results;
    }

}
