package com.example.kafkaconsumer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Schema(description = "서명된 오더 상세 DTO")
public class IfOrderDto {

    @Schema(description = "오더 번호")
    private String orderNo;

    @Schema(description = "품목명")
    private String itemName;

    @Schema(description = "품목 코드")
    private String itemCode;

    @Schema(description = "유효기간")
    private String expirationDate; // 필요 시 LocalDate로 변경 가능

    @Schema(description = "작업일자")
    private String workDate; // 필요 시 LocalDate로 변경 가능

    @Schema(description = "롯트번호")
    private String lotNo;

    @Schema(description = "서명자 사번")
    private String signEmpCd;
}
