package com.apiautomation.hook;

import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class BeforeAllHook {

  @BeforeAll(order = 0)
  public static void configure() throws ClassNotFoundException {
    RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    Class.forName("com.apiautomation.util.Endpoint");
  }

}
