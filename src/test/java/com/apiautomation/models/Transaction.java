package com.apiautomation.models;

import static java.lang.ThreadLocal.withInitial;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.hc.core5.http.Header;

@Getter
@Setter
public class Transaction {

  private static final ObjectMapper mapper = new ObjectMapper();

  // TODO Comment in to use your token
  // private String accessToken = Constants.QA_INITIAL_ACCESS_TOKEN;
  private String accessToken = "Test";


  private final ThreadLocal<CommonRequestContext> testContexts = withInitial(
      CommonRequestContext::new);

  public Transaction() {
  }

  private HttpResponse getResponse() {
    return testContexts.get().getResponse();
  }

  public void setResponse(HttpResponse response) {
    testContexts.get().setResponse(response);
  }

  public Object getObjectPayload() {
    return testContexts.get().getObjectPayload();
  }

  public String getPayloadAsString() {
    return testContexts.get().getJsonPayload();
  }

  public void setJsonPayload(String payloadAsString) {
    clearPayload();
    testContexts.get().setJsonPayload(payloadAsString);
  }

  public <T> T getObjectPayload(Class<T> clazz) {
    return testContexts.get().getObjectPayload(clazz);
  }

  public String getPayloadWithJson() {
    return testContexts.get().getPayloadWithJson();
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setObjectPayload(Object payload) {
    clearPayload();
    testContexts.get().setObjectPayload(payload);
  }

  private void clearPayload() {
    testContexts.get().setObjectPayload(null);
    testContexts.get().setJsonPayload(null);
  }

  public Header getHeader() {
    return testContexts.get().getHeader();
  }

  public String getValueFromResponse(String path) {
    return JsonPath.given(testContexts.get().getResponse().getBody()).getString(path);
  }

  public List<String> getValueListFromResponse(String path) {
    return JsonPath.given(testContexts.get().getResponse().getBody()).getList(path);
  }

  public void setUrl(String url) {
    testContexts.get().setUrl(url);
  }

  public String getUrl() {
    return testContexts.get().getUrl();
  }

  public int getResponseStatusCode() {
    return getResponse().getCode();
  }
}
