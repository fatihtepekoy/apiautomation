package com.apiautomation.util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AccessTokenProvider {

  private static final Map<String, String> accessTokenMap = Collections.synchronizedMap(
      new LinkedHashMap());

  public static List<String> getEmailList() {
    List<String> listEmails = new ArrayList<>(
        accessTokenMap.keySet()
                      .stream()
                      .filter(k -> !k.equals(Constants.QA_INITIAL_USERNAME))
                      .toList());
    Collections.reverse(listEmails);
    return listEmails;
  }

  public static String getInitialAccessToken() {
    String accessToken = accessTokenMap.get(Constants.QA_INITIAL_USERNAME);
    if (accessToken == null || accessToken.isEmpty()) {
      accessToken = RestAssured.given()
                               .contentType(ContentType.JSON)
                               .body(PayloadUtils.getBodyUserAndPass(Constants.QA_INITIAL_USERNAME,
                                   Constants.QA_INITIAL_PASSWORD))
                               .post(UriProvider.getAccessTokenUrl())
                               .jsonPath()
                               .get("data.accessToken");
      accessTokenMap.put(Constants.QA_INITIAL_USERNAME, accessToken);
    }
    return accessToken;
  }

}
