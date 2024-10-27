package com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response;

// 재무비율 DTO
public record FinancialRatioDto(
        String stac_yymm,      // 결산 년월
        String grs,            // 매출액 증가율
        String bsop_prfi_inrt, // 영업이익 증가율
        String roe,            // ROE
        String eps,            // EPS
        String bps,            // BPS
        String sps,            // 주당 매출액
        String lblt_rate       // 부채 비율
) {}