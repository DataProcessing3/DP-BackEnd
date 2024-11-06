package com.DataProcessing3.DP_BackEnd.domain.naverAPI.service;



import com.DataProcessing3.DP_BackEnd.domain.naverAPI.dto.BlogItem;
import com.DataProcessing3.DP_BackEnd.domain.naverAPI.dto.CafeItem;
import com.DataProcessing3.DP_BackEnd.domain.naverAPI.dto.NewsItem;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.ArrayList;

@Service
public class NaverSearchService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${naver.api.client-id}")
    private String clientId;

    @Value("${naver.api.client-secret}")
    private String clientSecret;

    private static final String NAVER_API_URL = "https://openapi.naver.com/v1/search/";


    // 제목 및 설명의 최대 길이 상수 설정
    private static final int MAX_TITLE_LENGTH = 20;
    private static final int MAX_DESCRIPTION_LENGTH = 30;

    private String trimText(String text, int maxLength) {
        // 텍스트가 최대 길이를 넘을 경우 자르고, 그렇지 않으면 그대로 반환
        if (text.length() > maxLength) {
            return text.substring(0, maxLength) + "...";  // 최대 길이까지 자르고 "..." 추가
        }
        return text;
    }

    //검색 결과 반환
    public List<?> search(String query, String type) {


        String url = NAVER_API_URL + type + "?query=" + query + "&display=5";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONObject json = new JSONObject(response.getBody());
        JSONArray items = json.getJSONArray("items");

        switch (type) {
                //뉴스 파트
            case "news":
                List<NewsItem> newsResults = new ArrayList<>();
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    NewsItem newsItem = new NewsItem();
                    newsItem.setTitle(trimText(Jsoup.parse(item.getString("title")).text(), MAX_TITLE_LENGTH));
                    newsItem.setOriginallink(item.getString("originallink"));
                    newsItem.setLink(item.getString("link"));
                    newsItem.setDescription(trimText(Jsoup.parse(item.getString("description")).text(), MAX_DESCRIPTION_LENGTH));
                    newsItem.setPubDate(item.getString("pubDate"));
                    newsResults.add(newsItem);
                }
                return newsResults;

                //블로그 파트
            case "blog":
                List<BlogItem> blogResults = new ArrayList<>();
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    BlogItem blogItem = new BlogItem();
                    blogItem.setTitle(trimText(Jsoup.parse(item.getString("title")).text(), MAX_TITLE_LENGTH));
                    blogItem.setLink(item.getString("link"));
                    blogItem.setDescription(trimText(Jsoup.parse(item.getString("description")).text(), MAX_DESCRIPTION_LENGTH));
                    blogItem.setBloggername(item.getString("bloggername"));
                    blogItem.setBloggerlink(item.getString("bloggerlink"));
                    blogItem.setPostdate(item.getString("postdate"));
                    blogResults.add(blogItem);
                }
                return blogResults;

                //카페 파트
            case "cafearticle":
                List<CafeItem> cafeResults = new ArrayList<>();
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    CafeItem cafeItem = new CafeItem();
                    cafeItem.setTitle(trimText(Jsoup.parse(item.getString("title")).text(), MAX_TITLE_LENGTH));
                    cafeItem.setLink(item.getString("link"));
                    cafeItem.setDescription(trimText(Jsoup.parse(item.getString("description")).text(),MAX_DESCRIPTION_LENGTH));
                    cafeItem.setCafename(item.getString("cafename"));
                    cafeItem.setCafeurl(item.getString("cafeurl"));
                    cafeResults.add(cafeItem);
                }
                return cafeResults;

            default:
                throw new IllegalArgumentException("Invalid search type");
        }
    }
}
