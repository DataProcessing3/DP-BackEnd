package com.DataProcessing3.DP_BackEnd.domain.naverAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsItem {

    // 필드 정의
    private String title;        // 뉴스 제목
    private String originallink; // 뉴스 원본 링크
    private String link;         // 네이버 뉴스 링크
    private String description;  // 뉴스 설명
    private String pubDate;      // 뉴스 게시 날짜



    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
