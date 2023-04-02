package com.apiautomation.models;

import static java.lang.ThreadLocal.withInitial;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

  // TODO Comment in to use your token
  // private String accessToken = Constants.QA_INITIAL_ACCESS_TOKEN;
  private String accessToken = "Test";

  private final ThreadLocal<CommonRequestContext> testContexts = withInitial(
      CommonRequestContext::new);

  public Transaction() {
  }

  public RequestSpecification getRequest() {
    return testContexts.get().getRequest();
  }

  private void setRequest(RequestSpecification requestSpecification) {
    testContexts.get().setRequest(requestSpecification);
  }

  public Response getResponse() {
    return testContexts.get().getResponse();
  }

  public void setResponse(Response response) {
    testContexts.get().setResponse(response);
  }

  public Object getPayload() {
    return testContexts.get().getObjectPayload();
  }

  public String getPayloadAsString() {
    return testContexts.get().getJsonPayload();
  }

  public void setJsonPayload(String payloadAsString) {
    testContexts.get().setJsonPayload(payloadAsString);
  }

  public <T> T getPayload(Class<T> clazz) {
    return testContexts.get().getObjectPayload(clazz);
  }

  public String getPayloadWithJson() {
    return testContexts.get().getPayloadWithJson();
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setObjectPayload(Object payload) {
    testContexts.get().setObjectPayload(payload);
  }

  public void clearPayload() {
    testContexts.get().setObjectPayload(null);
    testContexts.get().setJsonPayload(null);
  }

  public void setRequestWithJsonHeaders() {
    setRequest(RestAssured.given().header("Content-Type", "application/json"));
    clearPayload();
  }

  public String getValueFromResponse(String path) {
    return testContexts.get().getResponse().jsonPath().getString(path);
  }

  public List<String> getValueListFromResponse(String path) {
    return testContexts.get().getResponse().jsonPath().getList(path, String.class);
  }

  public void setUrl(String url) {
    testContexts.get().setUrl(url);
  }

  public String getUrl() {
    return testContexts.get().getUrl();
  }

  public int getResponseStatusCode() {
    return getResponse().getStatusCode();
  }
}
