package com.apiautomation.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.options.CurlOption.HttpMethod;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BaseOperation {

  public BaseOperation() {
  }

  protected static Response postRequest(Transaction transaction) {
    return sendRequest(transaction, HttpMethod.POST);
  }

  protected static Response getRequest(Transaction transaction) {
    return sendRequest(transaction, HttpMethod.GET);
  }

  protected static Response putRequest(Transaction transaction) {
    return sendRequest(transaction, HttpMethod.PUT);
  }

  protected static Response patchRequest(Transaction transaction) {
    return sendRequest(transaction, HttpMethod.PATCH);
  }

  private static String createJSONPayload(Object rawPayload) {
    ObjectMapper mapper = new ObjectMapper();
    String json;
    try {
      json = mapper.writeValueAsString(rawPayload);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Json create error", e);
    }
    return json;
  }

  private static Response sendRequest(Transaction transaction, HttpMethod httpMethod) {
    Response response = null;

    RequestSpecification request = transaction.getRequest();
    String accessToken = transaction.getAccessToken();
    Object objectPayload = transaction.getPayload();
    String jsonPayload = transaction.getPayloadAsString();
    String url = transaction.getUrl();

    if (jsonPayload != null) {
      request.body(jsonPayload);
    } else if (objectPayload != null) {
      request.body(createJSONPayload(objectPayload));
    }

    switch (httpMethod) {
      case GET -> response = request.auth().oauth2(accessToken).get(url);
      case POST -> response = request.auth().oauth2(accessToken).post(url);
      case DELETE -> response = request.auth().oauth2(accessToken).delete(url);
      case PUT -> response = request.auth().oauth2(accessToken).put(url);
      case PATCH -> response = request.auth().oauth2(accessToken).patch(url);
    }
    return response;
  }

}
