package com.apiautomation.models.dto.response;

import com.apiautomation.models.dto.DetailsDTO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResponseDTO {

  private String id;
  private String name;
  private DetailsDTO data;
  public Date createdAt;


}

