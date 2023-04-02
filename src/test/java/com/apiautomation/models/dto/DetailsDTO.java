package com.apiautomation.models.dto;

import com.apiautomation.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailsDTO {

  private int year;
  private float price;
  private String cPUModel;
  private String hardDiskSize;

  public static DetailsDTO generateDetailsPayload() {
    return DetailsDTO.builder()
                     .year(Constants.YEAR)
                     .price(Constants.PRICE)
                     .cPUModel(Constants.CPU_MODEL)
                     .hardDiskSize(Constants.HARD_DISK_SIZE)
                     .build();
  }

}