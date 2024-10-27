package com.DataProcessing3.DP_BackEnd.domain.koreaInvest.controller;

import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.request.BasicRequestDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.CapitalizationDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.FinancialRatioDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.ProfitDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.TradingVolumeDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.service.KisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping("/profit-loss")
    public Mono<List<ProfitDto>> getCapitalRank(@RequestBody BasicRequestDto basicDto){

        return kisService.getProfitLoss(basicDto);
    }

    // 재무비율 API
    @GetMapping("/financial-ratio/")
    public Mono<List<FinancialRatioDto>> getFinancialRatio(@RequestBody BasicRequestDto basicDto){

        return kisService.getfinancialRatio(basicDto);
    }

    // 거래량 순위 API
    @GetMapping("/volume-rank")
    public Mono<List<TradingVolumeDto>> getVolumeRank() {
        return kisService.customgetVolumeRank();
    }

}
