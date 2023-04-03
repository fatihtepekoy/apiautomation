package com.apiautomation.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;
import org.apache.hc.core5.http.Header;

@Data
public class CommonRequestContext {

  private HttpResponse response;
  private Header header;
  private Object objectPayload;
  private String jsonPayload;
  private String url;

  public Object getObjectPayload() {
    return objectPayload;
  }

  public <T> T getObjectPayload(Class<T> clazz) {
    return clazz.cast(objectPayload);
  }

  public <T> void setObjectPayload(T object) {
    objectPayload = object;
  }

  public String getPayloadWithJson() {
    String json;
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    try {
      json = ow.writeValueAsString(objectPayload);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return json;
  }

}
