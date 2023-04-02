package com.apiautomation.models.testcontext;

import com.apiautomation.models.dto.request.DeviceRequestDTO;
import com.apiautomation.models.dto.response.DeviceResponseDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class DeviceContext {

  private DeviceRequestDTO deviceRequestDTO;
  private DeviceResponseDTO deviceResponseDTO;
  private List<String> deviceIds = new ArrayList<>();

}
