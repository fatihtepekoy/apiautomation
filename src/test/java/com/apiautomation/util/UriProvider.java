package com.apiautomation.util;

public class UriProvider {

  private static final ConfigurationHolder configurationHolder = ConfigurationHolder.getInstance();

  public static String getBaseUri() {
    return configurationHolder.getProperty("baseUri");
  }

  public static String getAccessTokenUrl() {
    return Endpoint.UA_AUTH_ACCESS_TOKEN.getEndpoint();
  }
}
