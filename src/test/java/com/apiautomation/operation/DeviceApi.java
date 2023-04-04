package com.apiautomation.operation;

import com.apiautomation.models.BaseApi;
import com.apiautomation.models.HttpResponse;
import com.apiautomation.models.Transaction;
import com.apiautomation.models.dto.response.DeviceResponseDTO;
import com.apiautomation.models.testcontext.DeviceContext;
import com.apiautomation.util.UriProvider;
import com.apiautomation.util.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class DeviceApi extends BaseApi {


  public DeviceApi() {
    super();
  }

  public static HttpResponse create(Transaction transaction, DeviceContext deviceContext) {
    transaction.setUrl(Endpoint.OBJECTS.getEndpoint());
    HttpResponse response = postRequest(transaction);
    DeviceResponseDTO deviceResponseDTO = Utils.fromJson(response.getBody(),
        new TypeReference<DeviceResponseDTO>() {
        });
    deviceContext.setDeviceResponseDTO(deviceResponseDTO);
    return response;
  }

  public static HttpResponse update(Transaction transaction, DeviceContext deviceContext) {
    Map<String, String> parameters = new HashMap<>();
    parameters.put("id", deviceContext.getDeviceResponseDTO().getId());
    transaction.setUrl(Utils.generateUrl(Endpoint.OBJECTS_WITH_ID.getEndpoint(), parameters));
    return putRequest(transaction);
  }

  public static HttpResponse getDevices(Transaction transaction) {
    transaction.setUrl(Endpoint.OBJECTS.getEndpoint());
    return getRequest(transaction);
  }

  public static HttpResponse getDevice(Transaction transaction, DeviceContext deviceContext) {
    Map<String, String> parameters = new HashMap<>();
    parameters.put("id", deviceContext.getDeviceResponseDTO().getId());
    transaction.setUrl(Utils.generateUrl(Endpoint.OBJECTS_WITH_ID.getEndpoint(), parameters));
    return getRequest(transaction);
  }

  public static HttpResponse getSomeDevices(Transaction transaction, DeviceContext deviceContext) {
    List<NameValuePair> parameters = deviceContext.getDeviceIds()
                                                  .stream()
                                                  .map(deviceId -> new BasicNameValuePair("id",
                                                      deviceId))
                                                  .collect(Collectors.toList());
    transaction.setUrl(Utils.addQuery2Url(Endpoint.OBJECTS.getEndpoint(), parameters));
    return getRequest(transaction);
  }

  private enum Endpoint {

    OBJECTS("/objects"),
    OBJECTS_WITH_ID("/objects/{id}");

    Endpoint(String endpoint) {
      this.endpoint = endpoint;
    }

    private final String endpoint;


    public String getEndpoint() {
      return UriProvider.getBaseUri() + endpoint;
    }
  }

}

