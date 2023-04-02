package com.apiautomation.util;

public class PayloadUtils {

  public static String getBodyUserAndPass(String username, String password) {
    String body = """
        {"username": "%s", "password": "%s"}
        """;
    return String.format(body, username, password);
  }

  public static String getBodyForSettingPass(String password) {
    String body = """
        {"password": "%s",
        "passwordRepeat": "%s"}
        """;
    return String.format(body, password, password);
  }

  public static String getBodyForResetPasswordWithCurrent(String password) {
    String body = """
        {"currentPassword": "%s",
        "newPassword": "%s"}
        """;
    return String.format(body, Constants.QA_INITIAL_PASSWORD, password);
  }

  public static String getBodyForResetPassword(String password) {
    String body = """
        {"password": "%s"}
        """;
    return String.format(body, password);
  }

  public static String getBodyRefreshToken(String refreshToken) {
    String body = """
        {"refreshToken": "%s"}
        """;
    return String.format(body, refreshToken);
  }
}
