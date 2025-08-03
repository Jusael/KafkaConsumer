package com.example.kafkaconsumer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "서명된 포장지시 상세 DTO")
public class IfPackingOrderDto {

    @Schema(description = "포장 지시 번호")
    private String packingOrderNo;

    @Schema(description = "품목명")
    private String itemName;

    @Schema(description = "품목 코드")
    private String itemCode;

    @Schema(description = "유효기간")
    private String expirationDate; // LocalDate로 바꿔도 OK

    @Schema(description = "작업일자")
    private String workDate; // LocalDate로 바꿔도 OK

    @Schema(description = "롯트번호")
    private String lotNo;

    @Schema(description = "서명자 사번")
    private String signEmpCd;
}
