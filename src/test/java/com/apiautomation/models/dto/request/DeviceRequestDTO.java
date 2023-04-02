package com.apiautomation.models.dto.request;

import com.apiautomation.models.dto.DetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRequestDTO {

  private String name;
  private DetailsDTO data;

  public static DeviceRequestDTO generateDevicePayload() {
    return DeviceRequestDTO.builder()
                           .name("Apple MacBook Pro 16")
                           .data(DetailsDTO.generateDetailsPayload())
                           .build();
  }

}

