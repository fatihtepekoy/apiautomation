package com.apiautomation.models.dto.response;

import com.apiautomation.models.dto.DetailsDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceResponseDTO {

  private String id;
  private String name;
  private DetailsDTO data;
  public Date createdAt;

}

