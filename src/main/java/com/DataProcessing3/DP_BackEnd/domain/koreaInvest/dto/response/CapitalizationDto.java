package com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response;


// 시가총액, 주식 현재가
public record CapitalizationDto(
        String htsKorIsnm, // 종목코드
        String dataRank,   // 데이터 순위
        String stckAvls,   // 시가 총액
        String stckPrpr    // 주식 현재가
) {
}