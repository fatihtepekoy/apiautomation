package com.apiautomation.util;

public class Constants {


  public static final int YEAR = 2019;
  public static final float PRICE = 99.99f;
  public static final String CPU_MODEL = "Intel Core i9";
  public static final String HARD_DISK_SIZE = "1 TB";
  private static ConfigurationHolder configurationHolder = ConfigurationHolder.getInstance();

  public static final String QA_INITIAL_USERNAME = configurationHolder.getProperty(
      "oauth2.username");
  public static final String QA_INITIAL_PASSWORD = configurationHolder.getProperty(
      "oauth2.password");

  // TODO You need to comment in this line to provide the token to the transaction object
  //public static final String QA_INITIAL_ACCESS_TOKEN = AccessTokenProvider.getInitialAccessToken();
  public static final String QA_INITIAL_ACCESS_TOKEN = null;

  public static String QA_ACCESS_TOKEN = "";

}
