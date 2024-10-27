package com.DataProcessing3.DP_BackEnd.domain.koreaInvest.utils;

import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.CapitalizationDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.FinancialRatioDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.ProfitDto;
import com.DataProcessing3.DP_BackEnd.domain.koreaInvest.dto.response.TradingVolumeDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class KisParser {

    private final ObjectMapper objectMapper;

    public Mono<List<CapitalizationDto>> customparseCapitalRank(String response) {
        try {
            List<CapitalizationDto> responseDataList = new ArrayList<>();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode outputNode = rootNode.get("output");

            if (outputNode != null) {
                for (JsonNode node : outputNode) {
                    CapitalizationDto responseData = new CapitalizationDto(
                            node.get("hts_kor_isnm").asText(),
                            node.get("data_rank").asText(),
                            node.get("stck_prpr").asText(),
                            node.get("stck_avls").asText());
                    responseDataList.add(responseData);
                }
            }
            return Mono.just(responseDataList);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    // 손익계산서
    public Mono<List<ProfitDto>> customParsePriceAndLoss(String response) {
        try {
            List<ProfitDto> responseDataList = new ArrayList<>();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode outputNode = rootNode.get("output");

            if (outputNode != null) {
                for (JsonNode node : outputNode) {
                    ProfitDto responseData = new ProfitDto(
                            node.get("stac_yymm").asText(),
                            node.get("bsop_prti").asText(),
                            node.get("sale_account").asText(),
                            node.get("thtr_ntin").asText()
                    );
                    responseDataList.add(responseData);
                }
            }
            return Mono.just(responseDataList);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    // 재무비율
    public Mono<List<FinancialRatioDto>> customParsefinancialRatio(String response) {
        try {
            List<FinancialRatioDto> responseDataList = new ArrayList<>();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode outputNode = rootNode.get("output");

            if (outputNode != null) {
                for (JsonNode node : outputNode) {
                    FinancialRatioDto responseData = new FinancialRatioDto(
                            node.get("bsop_prfi_inrt").asText(),
                            node.get("lblt_rate").asText(),
                            node.get("stac_yymm").asText(),
                            node.get("grs").asText(),
                            node.get("eps").asText(),
                            node.get("roe_val").asText(),
                            node.get("sps").asText(),
                            node.get("bps").asText()
                    );
                    responseDataList.add(responseData);
                }
            }
            return Mono.just(responseDataList);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    // 거래량 순위 API
    public Mono<List<TradingVolumeDto>> customparseFVolumeRank(String response) {
        try {
            List<TradingVolumeDto> responseDataList = new ArrayList<>();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode outputNode = rootNode.get("output");
            if (outputNode != null) {
                for (JsonNode node : outputNode) {
                    TradingVolumeDto responseData = new TradingVolumeDto(
                            node.get("hts_kor_isnm").asText(),
                            node.get("data_rank").asText(),
                            node.get("stck_prpr").asText(),
                            node.get("avrg_vol").asText(),
                            node.get("vol_inrt").asText()
                    );

                    responseDataList.add(responseData);
                }
            }
            return Mono.just(responseDataList);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

}
