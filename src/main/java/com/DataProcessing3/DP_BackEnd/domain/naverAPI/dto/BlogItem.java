package com.DataProcessing3.DP_BackEnd.domain.naverAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogItem {

    private String title;       // 블로그 제목
    private String link;        // 블로그 링크
    private String description; // 블로그 설명
    private String bloggername; // 블로거 이름
    private String bloggerlink; // 블로거 링크
    private String postdate;    // 게시 날짜



    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }
}
