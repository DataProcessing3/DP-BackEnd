package com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response;

// 손익계산서 DTO
public record ProfitDto(
        // 결산 년도
         String stac_year,

        // 매출액
         String sale_account,

        // 영업 이익
         String bsop_prti,

        // 당기 순이익
         String thtr_ntin
) {
}
