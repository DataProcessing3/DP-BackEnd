package com.DataProcessing3.DP_BackEnd.domain.naverAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CafeItem {

    private String title;       // 카페 제목
    private String link;        // 카페 링크
    private String description; // 카페 설명
    private String cafename;    // 카페 이름
    private String cafeurl;     // 카페 URL



    public void setCafeurl(String cafeurl) {
        this.cafeurl = cafeurl;
    }
}
