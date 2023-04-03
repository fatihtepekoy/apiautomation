package com.apiautomation.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.options.CurlOption.HttpMethod;
import java.io.IOException;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;


public abstract class BaseOperation {

  public BaseOperation() {
  }

  protected static HttpResponse postRequest(Transaction transaction) {
    return sendRequest(transaction, HttpMethod.POST);
  }

  protected static HttpResponse getRequest(Transaction transaction) {
    return sendRequest(transaction, HttpMethod.GET);
  }

  protected static HttpResponse putRequest(Transaction transaction) {
    return sendRequest(transaction, HttpMethod.PUT);
  }

  protected static HttpResponse patchRequest(Transaction transaction) {
    return sendRequest(transaction, HttpMethod.PATCH);
  }

  private static HttpResponse sendRequest(Transaction transaction, HttpMethod httpMethod) {
    StringEntity entity = null;
    HttpResponse response = null;
    String accessToken = transaction.getAccessToken();
    String url = transaction.getUrl();
    Object objectPayload = transaction.getObjectPayload();
    String stringPayload = transaction.getPayloadAsString();
    Header header = transaction.getHeader();

    if (stringPayload != null) {
      entity = new StringEntity(stringPayload);
    } else if (objectPayload != null) {
      entity = new StringEntity(createJSONPayload(objectPayload));
    }

    logRequest(transaction, httpMethod);

    switch (httpMethod) {
      case GET -> {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(header);
        httpGet.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        response = get(httpGet);
      }
      case DELETE -> {
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.addHeader(header);
        httpDelete.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        response = delete(httpDelete);
      }
      case POST -> {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        httpPost.addHeader(header);
        httpPost.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        response = post(httpPost);
      }
      case PUT -> {
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(entity);
        httpPut.addHeader(header);
        httpPut.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        response = put(httpPut);
      }
      case PATCH -> {
        HttpPatch httpPatch = new HttpPatch(url);
        httpPatch.setEntity(entity);
        httpPatch.addHeader(header);
        httpPatch.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        response = patch(httpPatch);
      }
    }
    logResponse(response);

    return response;
  }

  private static HttpResponse get(HttpGet httpGet) {
    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
      return httpclient.execute(httpGet,
          response -> new HttpResponse(EntityUtils.toString(response.getEntity()),
              response.getCode()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static HttpResponse delete(HttpDelete httpDelete) {
    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
      return httpclient.execute(httpDelete,
          response -> new HttpResponse(EntityUtils.toString(response.getEntity()),
              response.getCode()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static HttpResponse post(HttpPost httpDelete) {
    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
      return httpclient.execute(httpDelete,
          response -> new HttpResponse(EntityUtils.toString(response.getEntity()),
              response.getCode()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static HttpResponse put(HttpPut httpPut) {
    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
      return httpclient.execute(httpPut,
          response -> new HttpResponse(EntityUtils.toString(response.getEntity()),
              response.getCode()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static HttpResponse patch(HttpPatch httpPatch) {
    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
      return httpclient.execute(httpPatch,
          response -> new HttpResponse(EntityUtils.toString(response.getEntity()),
              response.getCode()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
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

  private static void logRequest(Transaction transaction, HttpMethod httpMethod) {
    System.out.println("URL : " + transaction.getUrl());
    System.out.println("HTTP method : " + httpMethod.toString());
    System.out.println("Payload : " + transaction.getPayloadWithJson());
  }

  private static void logResponse(HttpResponse response) {
    System.out.println("Http response code : " + response.getCode());
    System.out.println("Http response body : \n" + response.getBody());
  }
}
