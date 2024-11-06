package com.DataProcessing3.DP_BackEnd.domain.koreaInvest.controller;

import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.request.BasicRequestDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.CapitalizationDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.FinancialRatioDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.ProfitDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.TradingVolumeDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.service.KisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class KisController {

    private final KisService kisService;

    // 시가총액, 주식 현재가
    @GetMapping("/capital-rank")
    public Mono<List<CapitalizationDto>> getCapitalRank(){

        return kisService.getCapitailRank();
    }

    // iscd = 005930
    // during = 0
    // 손익 계산서
    @GetMapping("/profit-loss/{iscd}/{during}")
    public Mono<List<ProfitDto>> getCapitalRank(@PathVariable String iscd,@PathVariable String during){

        return kisService.getProfitLoss(iscd, during);
    }

    // 재무비율 API
    @GetMapping("/financial-ratio/{iscd}/{during}")
    public Mono<List<FinancialRatioDto>> getFinancialRatio(@PathVariable String iscd,@PathVariable String during){

        return kisService.getfinancialRatio(iscd, during);
    }

    // 거래량 순위 API
    @GetMapping("/volume-rank")
    public Mono<List<TradingVolumeDto>> getVolumeRank() {
        return kisService.customgetVolumeRank();
    }

}
