package com.apiautomation.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

public class Utils {

  private static final Deque<Double> doubleDeque = new ArrayDeque<>();


  private static final String SOME_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  static {
    Random random = new Random();
    for (int i = 0; i < 100; i++) {
      double next = 1 + (100 - 1) * random.nextDouble();
      doubleDeque.push(next);
    }
  }

  public static String getUniqueEmail() {
    return "qaautomation__" + RandomStringUtils.random(20, SOME_CHARS)
        + "@qaautosuite.com";
  }

  public static String getRandomString() {
    return RandomStringUtils.random(12, SOME_CHARS);
  }

  public static <T> T fromJson(String json, TypeReference<T> typeRef) {
    T t;
    try {
      t = new ObjectMapper().readValue(json, typeRef);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return t;
  }


  public static String generateUrl(String template, Map<String, String> parameters) {
    StringBuilder newTemplate = new StringBuilder(template);
    List<Object> valueList = new ArrayList<>();

    Matcher matcher = Pattern.compile("\\{([^}]*)\\}").matcher(template);

    while (matcher.find()) {
      String key = matcher.group(1);

      String paramName = "{" + key + "}";
      int index = newTemplate.indexOf(paramName);
      if (index != -1) {
        if (parameters.get(key) != null) {
          newTemplate.replace(index, index + paramName.length(), "%s");
          valueList.add(parameters.get(key));
        }
      }
    }

    return String.format(newTemplate.toString(), valueList.toArray());
  }

  public static String addQuery2Url(String url, List<NameValuePair> parameters) {
    List<NameValuePair> nameValuePairs = parameters.stream()
                                                   .filter(k -> !k.getValue().isEmpty())
                                                   .toList();

    URIBuilder uriBuilder = null;
    String result = "";
    try {
      uriBuilder = new org.apache.http.client.utils.URIBuilder(url);
      result = uriBuilder.addParameters(nameValuePairs).build().toString();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }

    return result;

  }

  public static Double randomDoubleValue() {
    return doubleDeque.pop();
  }

}
