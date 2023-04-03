package com.apiautomation.helper;

import com.apiautomation.models.HttpResponse;
import com.apiautomation.models.Transaction;
import com.apiautomation.models.dto.request.DeviceRequestDTO;
import com.apiautomation.models.dto.response.DeviceResponseDTO;
import com.apiautomation.operation.DeviceOperations;
import com.apiautomation.util.Endpoint;
import com.apiautomation.util.Utils;
import com.fasterxml.jackson.core.type.TypeReference;

public class Helper {

  public static DeviceResponseDTO createDevice(DeviceRequestDTO deviceRequestDTO) {
    Transaction transaction = new Transaction();
    transaction.initRequestWithJsonHeaders();
    transaction.setUrl(Endpoint.OBJECTS.getEndpoint());
    transaction.setObjectPayload(deviceRequestDTO);
    HttpResponse response = DeviceOperations.create(transaction);
    return Utils.fromJson(response.getBody(), new TypeReference<DeviceResponseDTO>() {
    });
  }
}

