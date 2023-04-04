package com.apiautomation.helper;

import com.apiautomation.models.HttpResponse;
import com.apiautomation.models.Transaction;
import com.apiautomation.models.dto.request.DeviceRequestDTO;
import com.apiautomation.models.dto.response.DeviceResponseDTO;
import com.apiautomation.models.testcontext.DeviceContext;
import com.apiautomation.operation.DeviceApi;
import com.apiautomation.util.Utils;
import com.fasterxml.jackson.core.type.TypeReference;

public class Helper {

  public static DeviceResponseDTO createDevice(DeviceRequestDTO deviceRequestDTO) {
    Transaction transaction = new Transaction();
    transaction.setObjectPayload(deviceRequestDTO);
    HttpResponse response = DeviceApi.create(transaction, new DeviceContext());
    return Utils.fromJson(response.getBody(), new TypeReference<DeviceResponseDTO>() {
    });
  }
}

