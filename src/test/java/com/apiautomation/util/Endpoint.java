package com.apiautomation.util;


public enum Endpoint {

  OBJECTS("/objects"),
  OBJECTS_WITH_ID("/objects/{id}"),
  UA_AUTH_ACCESS_TOKEN("Your Access token endpoint");

  Endpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  private final String endpoint;


  public String getEndpoint() {
    return UriProvider.getBaseUri() + endpoint;
  }
}
