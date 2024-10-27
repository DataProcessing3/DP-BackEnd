package com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response;

// 거래량 순위
public record TradingVolumeDto(
        String htsKorIsnm, // 종목명
        String avrgVol,    // 평균 거래량
        String stckPrpr,   // 주식 현재가
        String dataRank,   // 데이터 순위
        String volInrt     // 거래량 증가율
) {}
