package com.DataProcessing3.DP_BackEnd.domain.koreaInvest.service;

import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.request.BasicRequestDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.CapitalizationDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.FinancialRatioDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.ProfitDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.TradingVolumeDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.utils.KisParser;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.utils.KisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class KisService {

    private final WebClient webClient;
    private final KisUtils kisUtils;
    private final KisParser kisParser;

    private final static String VOLUME_RANK = "FHPST01710000";
    private final static String CAPITIALRANK = "FHPST01740000";
    private final static String INQUIRE_PRICE = "FHKST01010100";
    private final static String PRICE_LOSS = "FHKST66430200";
    private final static String FINANCIAL_RATIO = "FHKST66430300";

    @Autowired
    public KisService(WebClient.Builder webClientBuilder, KisUtils kisUtils, KisParser kisParser) {
        this.webClient = webClientBuilder.baseUrl("https://openapi.koreainvestment.com:9443").build();
        this.kisUtils = kisUtils;
        this.kisParser = kisParser;
    }


    // 시가총액, 주식 현재가
    public Mono<List<CapitalizationDto>> getCapitailRank(){

        HttpHeaders headers =  kisUtils.createHttpHeaders(CAPITIALRANK);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/uapi/domestic-stock/v1/ranking/market-cap")
                        .queryParam("fid_input_price_2", "0")
                        .queryParam("fid_cond_mrkt_div_code", "J")
                        .queryParam("fid_cond_scr_div_code", "20174")
                        .queryParam("fid_div_cls_code","0")
                        .queryParam("fid_input_iscd","0002")
                        .queryParam("fid_trgt_cls_code","0")
                        .queryParam("fid_trgt_exls_cls_code", "0")
                        .queryParam("fid_input_price_1", "")
                        .queryParam("fid_vol_cnt","")
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> kisParser.customparseCapitalRank(response));
    }


    // 손익계산서
    public Mono<List<ProfitDto>> getProfitLoss(String iscd, String during){

        HttpHeaders headers = kisUtils.createHttpHeaders(PRICE_LOSS);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/uapi/domestic-stock/v1/finance/income-statement")
                        .queryParam("fid_cond_mrkt_div_code", "J")
                        .queryParam("fid_input_iscd",iscd)
                        .queryParam("FID_DIV_CLS_CODE",during) // 0. 년 기준 1. 분기 기준
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> kisParser.customParsePriceAndLoss(response));
    }


    // 재무비율 API
    public Mono<List<FinancialRatioDto>> getfinancialRatio(String iscd, String during){

        HttpHeaders headers = kisUtils.createHttpHeaders(FINANCIAL_RATIO);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/uapi/domestic-stock/v1/finance/financial-ratio")
                        .queryParam("fid_cond_mrkt_div_code", "J")
                        .queryParam("fid_input_iscd",iscd)
                        .queryParam("FID_DIV_CLS_CODE",during) // 0. 년 기준 1. 분기 기준
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> kisParser.customParsefinancialRatio(response));
    }

    public Mono<List<TradingVolumeDto>> customgetVolumeRank() {
        HttpHeaders headers =  kisUtils.createHttpHeaders(VOLUME_RANK);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/uapi/domestic-stock/v1/quotations/volume-rank")
                        .queryParam("FID_COND_MRKT_DIV_CODE", "J")
                        .queryParam("FID_COND_SCR_DIV_CODE", "20171")
                        .queryParam("FID_INPUT_ISCD", "0002")
                        .queryParam("FID_DIV_CLS_CODE", "0")
                        .queryParam("FID_BLNG_CLS_CODE", "0")
                        .queryParam("FID_TRGT_CLS_CODE", "111111111")
                        .queryParam("FID_TRGT_EXLS_CLS_CODE", "000000")
                        .queryParam("FID_INPUT_PRICE_1", "0")
                        .queryParam("FID_INPUT_PRICE_2", "0")
                        .queryParam("FID_VOL_CNT", "0")
                        .queryParam("FID_INPUT_DATE_1", "0")
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> kisParser.customparseFVolumeRank(response));

    }

}
