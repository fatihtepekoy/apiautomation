package com.apiautomation.helper;

import com.apiautomation.models.Transaction;
import com.apiautomation.models.dto.request.DeviceRequestDTO;
import com.apiautomation.models.dto.response.DeviceResponseDTO;
import com.apiautomation.operation.DeviceOperations;
import com.apiautomation.util.Endpoint;
import com.apiautomation.util.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.response.Response;

public class Helper {


  public static DeviceResponseDTO createDevice(DeviceRequestDTO deviceRequestDTO) {
    Transaction transaction = new Transaction();
    transaction.setRequestWithJsonHeaders();
    transaction.setUrl(Endpoint.OBJECTS.getEndpoint());
    transaction.setObjectPayload(deviceRequestDTO);
    Response response = DeviceOperations.create(transaction);
    return Utils.fromJson(response.getBody().asString(), new TypeReference<DeviceResponseDTO>() {
    });
  }
}
